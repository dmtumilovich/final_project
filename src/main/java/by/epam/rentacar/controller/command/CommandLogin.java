package by.epam.rentacar.controller.command;

import by.epam.rentacar.entity.User;
import by.epam.rentacar.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandLogin implements Command {


    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService service = new UserService();
        User user = service.login(username, password);

        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return "index.jsp";
        }

        return "jsp/login.jsp";
    }
}
