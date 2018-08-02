package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestParameters;
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

        AdminService adminService = ServiceFactory.getInstance().getAdminService();
        AddCarDTO addCarDTO = parseRequest(request);

        try {
            adminService.addCar(addCarDTO);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/controller?command=show_car_table");
    }

    private AddCarDTO parseRequest(HttpServletRequest request) {

        String brand = request.getParameter(RequestParameters.KEY_ADD_BRAND);
        String model = request.getParameter(RequestParameters.KEY_ADD_MODEL);
        String carClass = request.getParameter(RequestParameters.KEY_ADD_CLASS);
        String color = request.getParameter(RequestParameters.KEY_ADD_COLOR);
        int yearOfIssue = Integer.parseInt(request.getParameter(RequestParameters.KEY_ADD_YEAR));
        int numberOfSeats = Integer.parseInt(request.getParameter(RequestParameters.KEY_ADD_SEATS));
        double engineVolume = Double.parseDouble(request.getParameter(RequestParameters.KEY_ADD_ENGINE_VOLUME));
        double price = Double.parseDouble(request.getParameter(RequestParameters.KEY_ADD_PRICE));

        AddCarDTO addCarDTO = new AddCarDTO();
        addCarDTO.setBrand(brand);
        addCarDTO.setModel(model);
        addCarDTO.setCarClass(carClass);
        addCarDTO.setColor(color);
        addCarDTO.setYearOfIssue(yearOfIssue);
        addCarDTO.setNumberOfSeats(numberOfSeats);
        addCarDTO.setEngineVolume(engineVolume);
        addCarDTO.setPrice(price);

        return addCarDTO;

    }
}
