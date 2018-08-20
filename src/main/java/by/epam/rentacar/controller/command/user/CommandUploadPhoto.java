package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.util.UploadHelper;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class CommandUploadPhoto extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandUploadPhoto.class);

    private static final String USER_PHOTOS_DIRECTORY = "img/uploads/user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);
        Part filePart = request.getPart(RequestParameters.KEY_USER_PHOTO);

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + USER_PHOTOS_DIRECTORY;

        UserService userService = ServiceFactory.getInstance().getUserService();
        String destPage = request.getHeader(RequestHeader.KEY_REFERER);

        try {
            String filename = UploadHelper.writeFile(filePart, savePath);
            if (UploadHelper.checkFileFormat(filename)) {
                userService.setPhoto(userID, filename);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to update user photo!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);

    }

}
