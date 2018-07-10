package by.epam.rentacar.controller.command;

import by.epam.rentacar.entity.User;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.util.constant.PageParameters;
import by.epam.rentacar.util.constant.RequestParameters;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandSignup implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter(RequestParameters.KEY_USERNAME);
        String password = request.getParameter(RequestParameters.KEY_PASSWORD);
        String email = request.getParameter(RequestParameters.KEY_EMAIL);

        UserService service = new UserService();
        User user = service.signup(username, password, email);

        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + PageParameters.PAGE_MAIN);
            return;
        }

        response.sendRedirect(request.getContextPath() + PageParameters.PAGE_SIGNUP);
    }
}
