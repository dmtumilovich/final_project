package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.constant.*;
import by.epam.rentacar.domain.dto.FindCarsDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.InvalidDateRangeException;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The command to get all available cars in the selected date range.
 */
public class CommandGetCars implements Command {

    private static final Logger logger = LogManager.getLogger(CommandGetCars.class);

    /**
     * The result messages of the command execution.
     */
    private static final String MESSAGE_INVALID_DATE_RANGE = "local.find.error.invalid-date-range";

    /**
     * Gets search parameters from the request, such as start date, end date and car class.
     * Then processing this data by the service layer and receives list of the cars from it, puts
     * list as an attribute with name {@link RequestAttributes#KEY_CAR_LIST} to the request attributes and
     * redirects to the cars page.
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

        String pageStr = request.getParameter(RequestParameters.KEY_PAGE);
        int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);

        CarService carService = ServiceFactory.getInstance().getCarService();
        FindCarsDTO findCarsDTO = parseRequest(request);

        String destPage = PageParameters.PAGE_FIND_CARS;
        HttpSession session = request.getSession();

        try {

            List<Car> carList = carService.getCarsByDateRangeAndClass(findCarsDTO, page, 10);
            int pageCount = carService.getCarsPagesCount(findCarsDTO, 10);

            session.setAttribute(SessionAttributes.KEY_DATE_START, findCarsDTO.getDateStart());
            session.setAttribute(SessionAttributes.KEY_DATE_END, findCarsDTO.getDateEnd());

            request.setAttribute(RequestAttributes.KEY_CAR_CLASS, findCarsDTO.getCarClass());
            request.setAttribute(RequestAttributes.KEY_CAR_LIST, carList);
            request.setAttribute(RequestAttributes.KEY_PAGE, page);
            request.setAttribute(RequestAttributes.KEY_PAGE_COUNT, pageCount);
            request.getRequestDispatcher(PageParameters.PAGE_CARS).forward(request, response);
            return;

        } catch (InvalidDateRangeException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_INVALID_DATE_RANGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get all cars!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);

    }

    private FindCarsDTO parseRequest(HttpServletRequest request) {

        String dateStart = request.getParameter(RequestParameters.KEY_DATE_START);
        String dateEnd = request.getParameter(RequestParameters.KEY_DATE_END);
        String carClass = request.getParameter(RequestParameters.KEY_CAR_CLASS);

        FindCarsDTO findCarsDTO = new FindCarsDTO();
        findCarsDTO.setCarClass(carClass);
        findCarsDTO.setDateStart(dateStart);
        findCarsDTO.setDateEnd(dateEnd);

        return findCarsDTO;

    }
}
