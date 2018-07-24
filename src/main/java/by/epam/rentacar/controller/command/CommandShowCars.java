package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandShowCars implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CarService carService = ServiceFactory.getInstance().getCarService();

        List<Car> carList = null;
        try {

            carList = carService.getAllCars();
            request.setAttribute(RequestAttributes.KEY_CAR_LIST, carList);
            request.getRequestDispatcher(PageParameters.PAGE_CARS).forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
