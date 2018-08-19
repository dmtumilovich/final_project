package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for logging out the user from the system.
 */
public class CommandLogout implements Command {

    /**
     * Removes id and role attributes from the session.
     * If the user's role was {@link User.Role#ADMIN} redirects to the main page.
     * If not, redirects to the referer.
     *
     * @param request
     *          an {@link HttpServletRequest} object that contains client request
     * @param response
     *          an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException
     */
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
