package by.epam.rentacar.controller.command;

import by.epam.rentacar.util.constant.PageParameters;
import by.epam.rentacar.util.constant.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttributes.KEY_USER, null);

        String page = request.getHeader("referer");
        response.sendRedirect(page);
    }
}
