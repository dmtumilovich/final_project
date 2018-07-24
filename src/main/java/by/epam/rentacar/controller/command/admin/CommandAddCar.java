package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandAddCar implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String brand = request.getParameter("add_brand");
        String model = request.getParameter("add_model");
        String carClass = request.getParameter("add_class");
        String color = request.getParameter("add_color");
        int yearOfIssue = Integer.parseInt(request.getParameter("add_year"));
        int numberOfSeats = Integer.parseInt(request.getParameter("add_seats"));
        double engineVolume = Double.parseDouble(request.getParameter("add_engine_volume"));
        double price = Double.parseDouble(request.getParameter("add_price"));

        AddCarDTO addCarDTO = new AddCarDTO();
        addCarDTO.setBrand(brand);
        addCarDTO.setModel(model);
        addCarDTO.setCarClass(carClass);
        addCarDTO.setColor(color);
        addCarDTO.setYearOfIssue(yearOfIssue);
        addCarDTO.setNumberOfSeats(numberOfSeats);
        addCarDTO.setEngineVolume(engineVolume);
        addCarDTO.setPrice(price);

        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        try {
            adminService.addCar(addCarDTO);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/controller?command=show_car_table");
    }
}
