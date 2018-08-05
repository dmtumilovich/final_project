package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetEditProfile implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userID = ((User) request.getSession().getAttribute("user")).getId(); //переделать чтоб в сессии был только id, username и role

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;

        try {
            user = userService.getUser(userID);

            request.setAttribute("user_data", user);
            request.getRequestDispatcher(PageParameters.PAGE_EDIT_PROFILE).forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
