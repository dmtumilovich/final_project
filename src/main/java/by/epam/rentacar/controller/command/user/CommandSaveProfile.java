package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.InvalidInputDataException;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandSaveProfile extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandSaveProfile.class);

    private static final String PAGE_PROFILE = "/controller?command=profile";
    private static final String MESSAGE_PROFILE_CHANGED = "local.profile.success.profile-changed";
    private static final String MESSAGE_INVALID_DATA = "local.edit-profile.error.invalid-data";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        User user = parseRequest(request);
        UserService userService = ServiceFactory.getInstance().getUserService();

        String destPage = PAGE_PROFILE;
        HttpSession session = request.getSession();

        try {
            userService.editProfile(user);
            session.setAttribute(SessionAttributes.KEY_SUCCESS_MESSAGE, MESSAGE_PROFILE_CHANGED);
        } catch (InvalidInputDataException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_INVALID_DATA);
            destPage = PathHelper.getRefererPage(request);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to save user profile!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);
    }

    private User parseRequest(HttpServletRequest request) {

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);

        String editName = request.getParameter(RequestParameters.KEY_EDIT_NAME);
        String editSurname = request.getParameter(RequestParameters.KEY_EDIT_SURNAME);
        String editPhone = request.getParameter(RequestParameters.KEY_EDIT_PHONE);
        String editPassport = request.getParameter(RequestParameters.KEY_EDIT_PASSPORT);

        User user = new User();
        user.setId(userID);
        user.setName(editName);
        user.setSurname(editSurname);
        user.setPhone(editPhone);
        user.setPassport(editPassport);

        return user;

    }
}
