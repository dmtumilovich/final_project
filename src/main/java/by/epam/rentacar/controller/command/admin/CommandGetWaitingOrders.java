package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandGetWaitingOrders implements Command {

    public static final Logger logger = LogManager.getLogger(CommandGetWaitingOrders.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Order> orderList = null;
        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            orderList = orderService.getWaitingOrders();
            request.setAttribute(RequestAttributes.KEY_ORDER_LIST, orderList);
            request.getRequestDispatcher(PageParameters.PAGE_ADMIN_ORDERS).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get waiting orders", e);
        }

    }
}
