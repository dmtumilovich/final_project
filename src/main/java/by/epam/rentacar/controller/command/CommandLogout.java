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
        User.Role role = (User.Role) session.getAttribute(SessionAttributes.KEY_ROLE);

        session.removeAttribute(SessionAttributes.KEY_ID_USER);
        session.removeAttribute(SessionAttributes.KEY_ROLE);

        String referer = request.getHeader(RequestHeader.KEY_REFERER); //переделать то что снизу
        String destPage = (role == User.Role.ADMIN || role == User.Role.USER) ? PageParameters.PAGE_MAIN : referer;
        response.sendRedirect(destPage);
    }
}
