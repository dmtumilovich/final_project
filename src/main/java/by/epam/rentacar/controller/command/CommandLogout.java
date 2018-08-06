package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.removeAttribute(SessionAttributes.KEY_USER);

        String referer = request.getHeader(RequestHeader.KEY_REFERER); //переделать то что снизу
        String destPage = (referer.contains("user") || referer.contains("admin") || referer.contains("controller")) ? PageParameters.PAGE_MAIN : referer;
        response.sendRedirect(destPage);
    }
}
