package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestParameters;
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
        Car car = parseRequest(request);

        try {
            carService.editCar(car);

            response.sendRedirect("/controller?command=get_car_info&car_id=" + car.getId()); //ubrat
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to edit car info!", e);
        }

    }

    private Car parseRequest(HttpServletRequest request) {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        String brand = request.getParameter(RequestParameters.KEY_EDIT_BRAND);
        String model = request.getParameter(RequestParameters.KEY_EDIT_MODEL);
        String carClass = request.getParameter(RequestParameters.KEY_EDIT_CLASS);
        String color = request.getParameter(RequestParameters.KEY_EDIT_COLOR);
        int yearOfIssue = Integer.parseInt(request.getParameter(RequestParameters.KEY_EDIT_YEAR));
        int numberOfSeats = Integer.parseInt(request.getParameter(RequestParameters.KEY_EDIT_SEATS));
        double engineVolume = Double.parseDouble(request.getParameter(RequestParameters.KEY_EDIT_ENGINE_VOLUME));
        double price = Double.parseDouble(request.getParameter(RequestParameters.KEY_EDIT_PRICE));

        Car car = new Car(); //builder
        car.setId(carID);
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
