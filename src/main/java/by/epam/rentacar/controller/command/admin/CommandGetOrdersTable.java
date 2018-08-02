package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandGetOrdersTable implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Order> orderList = new ArrayList<>();

        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        try {
            orderList = adminService.getOrderList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("order_list", orderList);
        request.getRequestDispatcher("/jsp/admin/orders.jsp").forward(request, response);

    }

}
