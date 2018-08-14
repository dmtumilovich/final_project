package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetProfile extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandGetProfile.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;

        try {
            user = userService.getUser(userID);

            request.setAttribute(RequestAttributes.KEY_USER_DATA, user);
            request.getRequestDispatcher(PageParameters.PAGE_PROFILE).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get user profile!", e);
        }
    }
}
