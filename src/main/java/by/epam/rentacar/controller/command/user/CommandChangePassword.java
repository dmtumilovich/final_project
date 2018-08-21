package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.entity.Review;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.InvalidInputDataException;
import by.epam.rentacar.service.exception.PasswordsNotEqualException;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The command for changing password.
 */
public class CommandChangePassword extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandChangePassword.class);

    private static final String PAGE_PROFILE = "/controller?command=profile";

    /**
     * The result messages of the command execution.
     */
    private static final String MESSAGE_PASSWORD_CHANGED = "local.profile.success.password-changed";
    private static final String MESSAGE_INVALID_DATA = "local.change-password.error.invalid-data";
    private static final String MESSAGE_PASSWORDS_NOT_EQUAL = "local.change-password.error.passwords-not-equal";

    /**
     * Gets previous password, new password and confirmed new password from the request.
     * Then processing this data by service layer.
     * If data is valid redirects to the profile page with success message.
     * If {@link InvalidInputDataException} or {@link PasswordsNotEqualException} has happened
     * redirects to the previous page with appropriate error message.
     * If {@link ServiceException} has happened redirects to the error page.
     *
     * @param request
     *          an {@link HttpServletRequest} object that contains client request
     * @param response
     *          an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        ChangePasswordDTO changePasswordDTO = parseRequest(request);
        HttpSession session = request.getSession();
        String destPage = PathHelper.getPreviousPage(request);

        try {
            ServiceFactory.getInstance().getUserService().changePassword(changePasswordDTO);
            session.setAttribute(SessionAttributes.KEY_SUCCESS_MESSAGE, MESSAGE_PASSWORD_CHANGED);
            destPage = PAGE_PROFILE;
        } catch (InvalidInputDataException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_INVALID_DATA);
        } catch (PasswordsNotEqualException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_PASSWORDS_NOT_EQUAL);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to change password!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);
    }

    /**
     * Parses the request to get an {@link ChangePasswordDTO} object.
     *
     * @return an {@link ChangePasswordDTO} object.
     */
    private ChangePasswordDTO parseRequest(HttpServletRequest request) {

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);
        String previousPassword = request.getParameter(RequestParameters.KEY_PREVIOUS_PASSWORD);
        String newPassword = request.getParameter(RequestParameters.KEY_NEW_PASSWORD);
        String confirmPassword = request.getParameter(RequestParameters.KEY_CONFIRM_NEW_PASSWORD);

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setUserID(userID);
        changePasswordDTO.setPreviousPassword(previousPassword);
        changePasswordDTO.setNewPassword(newPassword);
        changePasswordDTO.setConfirmPassword(confirmPassword);

        return changePasswordDTO;

    }
}
