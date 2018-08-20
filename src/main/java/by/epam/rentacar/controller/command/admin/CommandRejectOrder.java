package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
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

public class CommandRejectOrder extends AdminCommand {

    private static final Logger logger = LogManager.getLogger(CommandRejectOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyAdmin(request)) {
            response.sendRedirect(PageParameters.PAGE_MAIN);
            return;
        }

        int orderID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_ORDER));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String destPage = PathHelper.getPreviousPage(request);

        try {
            orderService.updateStatus(orderID, Order.Status.REJECTED);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to reject order!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);

    }
}
