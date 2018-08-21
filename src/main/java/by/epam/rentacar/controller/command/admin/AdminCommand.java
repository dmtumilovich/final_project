package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * An abstract command for all admin's commands.
 * Defines methods, that check is user admin or not.
 */
public abstract class AdminCommand implements Command {

    /**
     * Gets {@code role} from session and checks it.
     *
     * @param request is the request.
     * @return {@code true} if role is {@link User.Role#ADMIN} or {@code false} if not.
     */
    protected boolean identifyAdmin(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User.Role role = (User.Role) session.getAttribute(SessionAttributes.KEY_ROLE);

        return isAdmin(role);

    }

    /**
     * Checks if role is {@link User.Role#ADMIN}.
     *
     * @param role is the role to check.
     * @return {@code true} if role is {@link User.Role#USER} or {@link User.Role#ADMIN} or {@code false} if not.
     */
    private boolean isAdmin(User.Role role) {

        return (role != null && role == User.Role.ADMIN );

    }

}
