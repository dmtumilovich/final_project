package by.epam.rentacar.controller.command;

import by.epam.rentacar.entity.User;
import by.epam.rentacar.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandSignup implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserService service = new UserService();
        User user = service.signup(username, password, email);

        if(user == null) {
            return "jsp/registration.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        return "index.jsp";

    }
}
