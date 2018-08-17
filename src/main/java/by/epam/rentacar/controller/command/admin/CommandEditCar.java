package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.CarDTO;
import by.epam.rentacar.domain.entity.Car;
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

public class CommandEditCar extends AdminCommand {

    private static final Logger logger = LogManager.getLogger(CommandEditCar.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyAdmin(request)) {
            response.sendRedirect(PageParameters.PAGE_MAIN);
            return;
        }

        CarService carService = ServiceFactory.getInstance().getCarService();
        CarDTO car = parseRequest(request);

        try {
            carService.editCar(car);

            String destPage = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(destPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to edit car info!", e);
        }

    }

    private CarDTO parseRequest(HttpServletRequest request) {

        String id = request.getParameter(RequestParameters.KEY_ID_CAR);
        String brand = request.getParameter(RequestParameters.KEY_EDIT_BRAND);
        String model = request.getParameter(RequestParameters.KEY_EDIT_MODEL);
        String carClass = request.getParameter(RequestParameters.KEY_EDIT_CLASS);
        String color = request.getParameter(RequestParameters.KEY_EDIT_COLOR);
        String yearOfIssue = request.getParameter(RequestParameters.KEY_EDIT_YEAR);
        String numberOfSeats = request.getParameter(RequestParameters.KEY_EDIT_SEATS);
        String engineVolume = request.getParameter(RequestParameters.KEY_EDIT_ENGINE_VOLUME);
        String price = request.getParameter(RequestParameters.KEY_EDIT_PRICE);

        CarDTO car = new CarDTO();
        car.setId(id);
        car.setBrand(brand);
        car.setModel(model);
        car.setCarClass(carClass);
        car.setColor(color);
        car.setYearOfIssue(yearOfIssue);
        car.setNumberOfSeats(numberOfSeats);
        car.setEngineVolume(engineVolume);
        car.setPrice(price);

        return car;

    }
}
