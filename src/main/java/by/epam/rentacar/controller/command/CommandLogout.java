package by.epam.rentacar.controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandLogout implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("username", "");
        return "index.jsp";
    }
}
