package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandDeleteCarPhoto implements Command {

    private static final Logger logger = LogManager.getLogger(CommandDeleteCarPhoto.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int photoID = Integer.parseInt(request.getParameter("photo_id"));
        System.out.println(photoID);

        CarService carService = ServiceFactory.getInstance().getCarService();

        try {
            carService.deletePhoto(photoID);
            String destPage = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(destPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to delete car photo", e);
        }


    }
}
