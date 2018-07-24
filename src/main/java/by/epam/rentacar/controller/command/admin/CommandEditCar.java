package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandEditCar implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));

        String brand = request.getParameter("edit_brand");
        String model = request.getParameter("edit_model");
        String carClass = request.getParameter("edit_class");
        String color = request.getParameter("edit_color");
        int yearOfIssue = Integer.parseInt(request.getParameter("edit_year"));
        int numberOfSeats = Integer.parseInt(request.getParameter("edit_seats"));
        double engineVolume = Double.parseDouble(request.getParameter("edit_engine_volume"));
        double price = Double.parseDouble(request.getParameter("edit_price"));

        Car car = new Car();
        car.setId(carID);
        car.setBrand(brand);
        car.setModel(model);
        car.setCarClass(carClass);
        car.setColor(color);
        car.setYearOfIssue(yearOfIssue);
        car.setNumberOfSeats(numberOfSeats);
        car.setEngineVolume(engineVolume);
        car.setPrice(price);

        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        try {
            adminService.editCar(car);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/controller?command=get_car_info&car_id=" + carID);

    }
}
