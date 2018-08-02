package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandRejectOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int orderID = Integer.parseInt(request.getParameter("order_id"));

        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        try {
            adminService.rejectOrder(orderID);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        String destPage = request.getHeader(RequestHeader.KEY_REFERER);
        response.sendRedirect(destPage);

    }
}
