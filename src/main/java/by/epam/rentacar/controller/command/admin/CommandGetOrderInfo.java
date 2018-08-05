package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
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

        int orderID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ORDER_ID));

        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        OrderInfoDTO orderInfo = new OrderInfoDTO();

        try {
            orderInfo = adminService.getOrderInfo(orderID);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(RequestAttributes.KEY_ORDER_INFO, orderInfo);
        request.getRequestDispatcher(PageParameters.PAGE_ADMIN_ORDER).forward(request, response);

    }
}
