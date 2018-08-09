package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandUpdateStatus implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int orderID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_ORDER));
        String status = request.getParameter("status");

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            orderService.updateStatus(orderID, status);
            String destPage = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(destPage);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
