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

public class CommandGetEditProfile implements Command {

    private static final Logger logger = LogManager.getLogger(CommandGetEditProfile.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userID = ((User) request.getSession().getAttribute(SessionAttributes.KEY_USER)).getId(); //переделать чтоб в сессии был только id, username и role

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;

        try {
            user = userService.getUser(userID);

            request.setAttribute(RequestAttributes.KEY_USER_DATA, user);
            request.getRequestDispatcher(PageParameters.PAGE_EDIT_PROFILE).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get edit profile page!", e);
        }
    }
}
