package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.ServiceFactory;
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

public class CommandUploadCarPhoto extends AdminCommand {

    private static final Logger logger = LogManager.getLogger(CommandUploadCarPhoto.class);

    private static final String CARS_PHOTOS_DIRECTORY = "img/uploads/cars";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyAdmin(request)) {
            response.sendRedirect(PageParameters.PAGE_MAIN);
            return;
        }

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        Part part = request.getPart("car_photo");

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + CARS_PHOTOS_DIRECTORY;

        String filename = writeFile(part, savePath);

        logger.log(Level.DEBUG, "car file name: " + filename);

        CarService carService = ServiceFactory.getInstance().getCarService();

        try {
            carService.addPhoto(carID, filename);
            logger.log(Level.DEBUG, "Photo has been added to the car with id=" + carID);
            String destPage = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(destPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to add car photo!", e);
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
