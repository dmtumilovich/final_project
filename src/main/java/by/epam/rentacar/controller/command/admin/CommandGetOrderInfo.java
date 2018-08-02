package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetOrderInfo implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int orderID = Integer.parseInt(request.getParameter("order_id"));

        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        OrderInfoDTO orderInfo = new OrderInfoDTO();

        try {
            orderInfo = adminService.getOrderInfo(orderID);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("order_info", orderInfo);
        request.getRequestDispatcher("/jsp/admin/order.jsp").forward(request, response);

    }
}
