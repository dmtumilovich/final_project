package by.epam.rentacar.controller.command;

import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandEditProfile implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributes.KEY_USER);

        String editName = request.getParameter(RequestParameters.KEY_EDIT_NAME);
        String editSurname = request.getParameter(RequestParameters.KEY_EDIT_SURNAME);
        String editPhone = request.getParameter(RequestParameters.KEY_EDIT_PHONE);
        String editPassport =request.getParameter(RequestParameters.KEY_EDIT_PASSPORT);

        user.setName(editName);
        user.setSurname(editSurname);
        user.setPassport(editPassport);
        user.setPhone(editPhone);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try {

            if(userService.editProfile(user)) {
                session.setAttribute(SessionAttributes.KEY_USER, user);
                response.sendRedirect(request.getContextPath() + PageParameters.PAGE_PROFILE);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
