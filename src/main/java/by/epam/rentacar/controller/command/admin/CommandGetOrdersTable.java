package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.AdminService;
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
import java.util.ArrayList;
import java.util.List;

public class CommandGetOrdersTable extends AdminCommand {

    private static final Logger logger = LogManager.getLogger(CommandGetOrdersTable.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyAdmin(request)) {
            response.sendRedirect(PageParameters.PAGE_MAIN);
            return;
        }

        String pageStr = request.getParameter(RequestParameters.KEY_PAGE);
        int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);
        String status = request.getParameter(RequestParameters.KEY_ORDER_STATUS);

        List<Order> orderList = null;

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            orderList = (status == null) ? orderService.getAllOrders(page, 10) : orderService.getOrdersByStatus(status, page, 10);
            int pagesCount = orderService.getOrdersPagesCountByStatus(10, status);

            request.setAttribute(RequestAttributes.KEY_PAGE, page);
            request.setAttribute(RequestAttributes.KEY_PAGE_COUNT, pagesCount);
            request.setAttribute(RequestAttributes.KEY_ORDER_LIST, orderList);
            request.setAttribute(RequestAttributes.KEY_ORDER_STATUS, status);
            request.getRequestDispatcher(PageParameters.PAGE_ADMIN_ORDERS).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get orders!", e);
        }

    }

}
