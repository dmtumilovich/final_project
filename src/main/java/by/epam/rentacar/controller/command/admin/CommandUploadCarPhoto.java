package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.UploadHelper;
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
        Part part = request.getPart(RequestParameters.KEY_CAR_PHOTO);

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + CARS_PHOTOS_DIRECTORY;

        CarService carService = ServiceFactory.getInstance().getCarService();
        String destPage = request.getHeader(RequestHeader.KEY_REFERER);

        try {
            String filename = UploadHelper.writeFile(part, savePath);
            if (UploadHelper.checkFileFormat(filename)) {
                carService.addPhoto(carID, filename);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to add car photo!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);

    }
}
