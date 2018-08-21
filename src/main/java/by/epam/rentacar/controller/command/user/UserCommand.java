package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * An abstract command for all user's commands.
 */
public abstract class UserCommand implements Command {

    /**
     * Gets {@code role} from session and checks it.
     *
     * @param request is the request.
     * @return {@code true} if role is {@link User.Role#USER} or {@link User.Role#ADMIN} or {@code false} if not.
     */
    protected boolean identifyUser(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User.Role role = (User.Role) session.getAttribute(SessionAttributes.KEY_ROLE);

        return isUserOrAdmin(role);

    }

    /**
     * Checks if role is {@link User.Role#USER} or {@link User.Role#ADMIN}.
     *
     * @param role is the role to check.
     * @return {@code true} if role is {@link User.Role#USER} or {@link User.Role#ADMIN} or {@code false} if not.
     */
    private boolean isUserOrAdmin(User.Role role) {

        return (role != null && (role == User.Role.USER || role == User.Role.ADMIN));

    }

}
