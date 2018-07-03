package by.epam.rentacar.controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandLanguage implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        return "index.jsp";
    }
}
