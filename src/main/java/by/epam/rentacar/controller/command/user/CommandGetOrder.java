package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int orderID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ORDER_ID));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            UserOrderDTO userOrderDTO = orderService.getUserOrder(orderID);
            request.setAttribute("order_info", userOrderDTO);
            request.getRequestDispatcher("/user/order").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
