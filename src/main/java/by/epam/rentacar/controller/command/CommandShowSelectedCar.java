package by.epam.rentacar.controller.command;

import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandShowSelectedCar implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        CarService carService = ServiceFactory.getInstance().getCarService();
        Car car = null;

        try {

            car = carService.getSelectedCar(carID);
            request.setAttribute(RequestAttributes.KEY_CAR, car);
            request.getRequestDispatcher(PageParameters.PAGE_CAR).forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
