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

/**
 * The command to get selected car.
 */
public class CommandGetCar implements Command {

    private static final Logger logger = LogManager.getLogger(CommandGetCar.class);

    /**
     * Gets id of the the car from the request. Then receives car data from the service layer and puts
     * it as an attribute with name {@link RequestAttributes#KEY_CAR} to request attributes.
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

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));

        CarService carService = ServiceFactory.getInstance().getCarService();

        try {

            Car car = carService.getCar(carID);
            request.setAttribute(RequestAttributes.KEY_CAR, car);
            request.getRequestDispatcher(PageParameters.PAGE_CAR).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get selected car!", e);
        }
    }
}
