package by.epam.rentacar.controller.command;

import by.epam.rentacar.domain.dto.SigninDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.InvalidInputDataException;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import com.sun.media.sound.InvalidDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandSignin implements Command {

    private static final Logger logger = LogManager.getLogger(CommandSignin.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        SigninDTO signinDTO = parseRequest(request);
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;

        try {
            user = userService.login(signinDTO);

            if (user != null) {
                int userID = user.getId();
                User.Role role = user.getRole();

                HttpSession session = request.getSession();
                session.setAttribute(SessionAttributes.KEY_ID_USER, userID);
                session.setAttribute(SessionAttributes.KEY_ROLE, role);

                String destPage = (role == User.Role.ADMIN) ? PageParameters.PAGE_ADMIN_PANEL : PageParameters.PAGE_MAIN;
                response.sendRedirect(request.getContextPath() + destPage);
            } else {
                request.setAttribute(RequestAttributes.KEY_ERROR_MESSAGE, "Wrong username or password!");
                request.getRequestDispatcher(PageParameters.PAGE_SIGNIN).forward(request, response);
            }

        } catch (InvalidInputDataException e) {
            request.setAttribute(RequestAttributes.KEY_ERROR_MESSAGE, "Check the data you entered!");
            request.getRequestDispatcher(PageParameters.PAGE_SIGNIN).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Signing in failed!", e);
        }

    }

    public SigninDTO parseRequest(HttpServletRequest request) {

        String username = request.getParameter(RequestParameters.KEY_USERNAME);
        String password = request.getParameter(RequestParameters.KEY_PASSWORD);

        SigninDTO signinDTO = new SigninDTO();
        signinDTO.setUsername(username);
        signinDTO.setPassword(password);

        return signinDTO;

    }
}
