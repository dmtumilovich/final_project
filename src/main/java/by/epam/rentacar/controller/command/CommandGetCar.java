package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
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
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();
        String dateStart = (String) session.getAttribute(SessionAttributes.KEY_DATE_START);
        String dateEnd = (String) session.getAttribute(SessionAttributes.KEY_DATE_END);

        CarService carService = ServiceFactory.getInstance().getCarService();
        String destPage;

        try {

            Car car = carService.getCar(carID);
            boolean isAvailableToRent = carService.isAvailableToRent(carID, dateStart, dateEnd);
            if (car != null) {
                request.setAttribute(RequestAttributes.KEY_CAR, car);
                request.setAttribute(RequestAttributes.KEY_IS_AVAILABLE, isAvailableToRent);
                request.getRequestDispatcher(PageParameters.PAGE_CAR).forward(request, response);
                return;
            } else {
                destPage = PageParameters.PAGE_ERROR;
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get selected car!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);
    }
}
