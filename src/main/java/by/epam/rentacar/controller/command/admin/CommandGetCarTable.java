package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandGetCarTable implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        AdminService adminService = ServiceFactory.getInstance().getAdminService();
        List<CarItemDTO> carList = new ArrayList<>();

        try {
            carList = adminService.getCarList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(RequestAttributes.KEY_CAR_LIST, carList);
        request.getRequestDispatcher(PageParameters.PAGE_ADMIN_CARS).forward(request, response);

    }
}
