package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AdminCommand implements Command {

    protected boolean identifyAdmin(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User.Role role = (User.Role) session.getAttribute(SessionAttributes.KEY_ROLE);

        return isAdmin(role);

    }

    private boolean isAdmin(User.Role role) {

        return (role != null && role == User.Role.ADMIN );

    }

}
