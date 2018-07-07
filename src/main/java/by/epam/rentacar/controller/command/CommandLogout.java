package by.epam.rentacar.controller.command;

import by.epam.rentacar.util.constant.PageParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("username", "");
        session.invalidate();

        response.sendRedirect(request.getContextPath() + PageParameters.PAGE_MAIN);
    }
}
