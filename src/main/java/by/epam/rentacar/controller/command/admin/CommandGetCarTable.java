package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
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
import java.util.ArrayList;
import java.util.List;

//rename
public class CommandGetCarTable extends AdminCommand {

    private static final Logger logger = LogManager.getLogger(CommandGetCarTable.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyAdmin(request)) {
            response.sendRedirect(PageParameters.PAGE_MAIN);
            return;
        }

        String pageStr = request.getParameter(RequestParameters.KEY_PAGE);
        int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);

        CarService carService = ServiceFactory.getInstance().getCarService();

        try {
            List<Car> carList = carService.getAllNotDeletedCars(page, 10);
            int pageCount = carService.getCarsPagesCount(10);

            request.setAttribute(RequestAttributes.KEY_PAGE, page);
            request.setAttribute(RequestAttributes.KEY_PAGE_COUNT, pageCount);
            request.setAttribute(RequestAttributes.KEY_CAR_LIST, carList);
            request.getRequestDispatcher(PageParameters.PAGE_ADMIN_CARS).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get cars!", e);
        }

    }
}
