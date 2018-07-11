package by.epam.rentacar.controller.command;

import by.epam.rentacar.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.util.constant.PageParameters;
import by.epam.rentacar.util.constant.RequestAttributes;
import by.epam.rentacar.util.constant.RequestParameters;
import by.epam.rentacar.util.constant.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandSignin implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter(RequestParameters.KEY_USERNAME);
        String password = request.getParameter(RequestParameters.KEY_PASSWORD);

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;

        try {
            user = userService.login(username, password);

            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttributes.KEY_USER, user);
                response.sendRedirect(request.getContextPath() + PageParameters.PAGE_MAIN);
            } else {
                request.setAttribute(RequestAttributes.KEY_INCORRECT_DATA, true);
                request.getRequestDispatcher(PageParameters.PAGE_SIGNIN).forward(request, response);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
