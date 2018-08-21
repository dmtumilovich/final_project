package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
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

/**
 * The command for adding car.
 */
public class CommandAddCar extends AdminCommand {

    private static final Logger logger = LogManager.getLogger(CommandAddCar.class);

    private static final String PAGE_CARS = "/controller?command=show_car_table";

    /**
     * Gets car data from the request and processing it by the service layer.
     * If data is valid redirects to the cars page.
     * If not redirects to the error page.
     *
     * @param request
     *          an {@link HttpServletRequest} object that contains client request
     * @param response
     *          an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyAdmin(request)) {
            response.sendRedirect(PageParameters.PAGE_MAIN);
            return;
        }

        CarDTO car = parseRequest(request);
        CarService carService = ServiceFactory.getInstance().getCarService();
        String destPage;

        try {
            carService.addCar(car);
            destPage = PAGE_CARS;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to add car!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);

    }

    /**
     * Parses the request to get an {@link CarDTO} object.
     *
     * @return and {@link CarDTO} object.
     */
    private CarDTO parseRequest(HttpServletRequest request) {

        String id = request.getParameter(RequestParameters.KEY_ID_CAR);
        String brand = request.getParameter(RequestParameters.KEY_ADD_BRAND);
        String model = request.getParameter(RequestParameters.KEY_ADD_MODEL);
        String carClass = request.getParameter(RequestParameters.KEY_ADD_CLASS);
        String color = request.getParameter(RequestParameters.KEY_ADD_COLOR);
        String yearOfIssue = request.getParameter(RequestParameters.KEY_ADD_YEAR);
        String numberOfSeats = request.getParameter(RequestParameters.KEY_ADD_SEATS);
        String engineVolume = request.getParameter(RequestParameters.KEY_ADD_ENGINE_VOLUME);
        String price = request.getParameter(RequestParameters.KEY_ADD_PRICE);

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
