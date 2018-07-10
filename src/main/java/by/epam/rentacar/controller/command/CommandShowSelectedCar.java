package by.epam.rentacar.controller.command;

import by.epam.rentacar.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.util.constant.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandShowSelectedCar implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter(RequestParameters.KEY_ID_CAR));
        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR)); //проверка на null
        //exception???
        CarService service = new CarService();
        Car car = service.getSelectedCar(carID);
        request.setAttribute("car", car);
        try {
            request.getRequestDispatcher("/car").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
