package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestHeader;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CommandUploadPhoto implements Command {

    private static final Logger logger = LogManager.getLogger(CommandUploadPhoto.class);

    private static final String USER_PHOTOS_DIRECTORY = "img/uploads/user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);
        Part filePart = request.getPart("user_photo");

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + USER_PHOTOS_DIRECTORY;

        String filename = writeFile(filePart, savePath);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            userService.setPhoto(userID, filename);
            logger.log(Level.DEBUG, "user with id=" + userID + " has changed his photo!");
            String destPage = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(destPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to update user photo!", e);
        }

    }

    private String writeFile(Part filePart, String directory) throws IOException {

        File fileDir = new File(directory);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String format = getFileFormat(filePart);
        File file = File.createTempFile("photo", format, fileDir);

        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return file.getName();

    }

    private String getFileFormat(Part filePart) {

        String filename = getFileName(filePart);

        if (filename == null) {
            //exception
        }

        return filename.substring(filename.lastIndexOf("."));

    }

    private String getFileName(Part filePart) {

        for (String content : filePart.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") +1).trim().replace("\"", "");
            }
        }

        return null;
    }

}
