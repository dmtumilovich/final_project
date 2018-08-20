package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class UserCommand implements Command {

    protected boolean identifyUser(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User.Role role = (User.Role) session.getAttribute(SessionAttributes.KEY_ROLE);

        return isUserOrAdmin(role);

    }

    private boolean isUserOrAdmin(User.Role role) {

        return (role != null && (role == User.Role.USER || role == User.Role.ADMIN));

    }

}
