package by.epam.rentacar.controller.command;

import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetCar implements Command {

    private static final Logger logger = LogManager.getLogger(CommandGetCar.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));

        CarService carService = ServiceFactory.getInstance().getCarService();
        Car car = null;

        try {

            car = carService.getCar(carID);
            request.setAttribute(RequestAttributes.KEY_CAR, car);
            request.getRequestDispatcher(PageParameters.PAGE_CAR).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get selected car!", e);
        }
    }
}
