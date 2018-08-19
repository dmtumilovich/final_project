package by.epam.rentacar.controller.command;

import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.*;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
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
 * The command for signing up the user.
 */
public class CommandSignup implements Command {

    private static final Logger logger = LogManager.getLogger(CommandSignup.class);

    /**
     * The result messages of the command execution.
     */
    private static final String MESSAGE_USERNAME_EXISTS = "local.signup.error.username-exists";
    private static final String MESSAGE_EMAIL_EXISTS = "local.signup.error.email-exists";
    private static final String MESSAGE_INVALID_DATA = "local.signup.error.invalid-data";
    private static final String MESSAGE_PASSWORDS_NOT_EQUAL = "local.signup.error.passwords-not-equal";

    /**
     * Gets sign up input data from the request. Then processing this data by service layer.
     * If data is valid and sign up is successful, redirects to the main page.
     * If not, redirects to the sign up page with appropriate error message.
     * @param request
     *          an {@link HttpServletRequest} object that contains client request
     * @param response
     *          an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        SignupDTO signupDTO = parseRequest(request);

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;

        HttpSession session = request.getSession();
        String destPage = PageParameters.PAGE_SIGNUP;

        try {

            user = userService.signup(signupDTO);

            if(user != null) {

                int userID = user.getId();
                User.Role role = user.getRole();

                session.setAttribute(SessionAttributes.KEY_ID_USER, userID);
                session.setAttribute(SessionAttributes.KEY_ROLE, role);
                destPage = PageParameters.PAGE_MAIN;
            }

        } catch (UsernameAlreadyExistsException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_USERNAME_EXISTS);
        } catch (EmailAlreadyExistsException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_EMAIL_EXISTS);
        } catch (InvalidInputDataException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_INVALID_DATA);
        } catch (PasswordsNotEqualException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_PASSWORDS_NOT_EQUAL);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Signup failed!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);

    }

    private SignupDTO parseRequest(HttpServletRequest request) {

        String username = request.getParameter(RequestParameters.KEY_USERNAME);
        String password = request.getParameter(RequestParameters.KEY_PASSWORD);
        String confirmPassword = request.getParameter(RequestParameters.KEY_CONFIRM_PASSWORD);
        String email = request.getParameter(RequestParameters.KEY_EMAIL);

        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setUsername(username);
        signupDTO.setPassword(password);
        signupDTO.setConfirmPassword(confirmPassword);
        signupDTO.setEmail(email);

        return signupDTO;

    }

}
