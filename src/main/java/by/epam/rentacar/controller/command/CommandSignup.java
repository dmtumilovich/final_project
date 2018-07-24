package by.epam.rentacar.controller.command;

import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.EmailAlreadyExistsException;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.service.exception.UsernameAlreadyExistsException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandSignup implements Command {

    private static final String MESSAGE_USERNAME_EXISTS = "local.signup.text.username-exists";
    private static final String MESSAGE_EMAIL_EXISTS = "local.signup.text.email-exists";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter(RequestParameters.KEY_USERNAME);
        String password = request.getParameter(RequestParameters.KEY_PASSWORD);
        String confirmPassword = request.getParameter(RequestParameters.KEY_CONFIRM_PASSWORD);
        String email = request.getParameter(RequestParameters.KEY_EMAIL);

        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setUsername(username);
        signupDTO.setPassword(password);
        signupDTO.setConfirmPassword(confirmPassword);
        signupDTO.setEmail(email);

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;

        try {

            user = userService.signup(signupDTO);

            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttributes.KEY_USER, user);
                response.sendRedirect(request.getContextPath() + PageParameters.PAGE_MAIN);
                return;
            }

        } catch (UsernameAlreadyExistsException e) {
            request.setAttribute(RequestAttributes.KEY_SIGNUP_FAILED, true);
            request.setAttribute(RequestAttributes.KEY_SIGNUP_FAILED_MESSAGE, MESSAGE_USERNAME_EXISTS);
            request.getRequestDispatcher(PageParameters.PAGE_SIGNUP).forward(request, response);
        } catch (EmailAlreadyExistsException e) {
            request.setAttribute(RequestAttributes.KEY_SIGNUP_FAILED, true);
            request.setAttribute(RequestAttributes.KEY_SIGNUP_FAILED_MESSAGE, MESSAGE_EMAIL_EXISTS);
            request.getRequestDispatcher(PageParameters.PAGE_SIGNUP).forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect(PageParameters.PAGE_ERROR);
        }


    }
}
