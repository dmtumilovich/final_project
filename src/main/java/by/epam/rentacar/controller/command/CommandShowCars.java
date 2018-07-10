package by.epam.rentacar.controller.command;

import by.epam.rentacar.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.util.constant.PageParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandShowCars implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("command show_cars");
        List<Car> carList = new CarService().getAllCars();
        request.setAttribute("car_list", carList);
        try {
            request.getRequestDispatcher(PageParameters.PAGE_CARS).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            //подумать, что делать с этим эксепшеном
        }
    }
}
