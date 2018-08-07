package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandChangePassword implements Command {

    private static final Logger logger = LogManager.getLogger(CommandChangePassword.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ChangePasswordDTO changePasswordDTO = parseRequest(request);

        try {
            ServiceFactory.getInstance().getUserService().changePassword(changePasswordDTO);
            response.sendRedirect(PageParameters.PAGE_PROFILE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to change password!", e);
        }
    }

    private ChangePasswordDTO parseRequest(HttpServletRequest request) {

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);
        String previousPassword = request.getParameter(RequestParameters.KEY_EDIT_PREVIOUS_PASSWORD);
        String newPassword = request.getParameter(RequestParameters.KEY_EDIT_NEW_PASSWORD);
        String confirmPassword = request.getParameter(RequestParameters.KEY_EDIT_CONFIRM_PASSWORD);

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setUserID(userID);
        changePasswordDTO.setPreviousPassword(previousPassword);
        changePasswordDTO.setNewPassword(newPassword);
        changePasswordDTO.setConfirmPassword(confirmPassword);

        return changePasswordDTO;

    }
}
