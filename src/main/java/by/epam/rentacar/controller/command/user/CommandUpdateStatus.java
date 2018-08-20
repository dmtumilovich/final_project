package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestParameters;
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

public class CommandUpdateStatus extends UserCommand {

    public static final Logger logger = LogManager.getLogger(CommandUpdateStatus.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int orderID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_ORDER));
        String status = request.getParameter(RequestParameters.KEY_ORDER_STATUS);

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String destPage = PathHelper.getPreviousPage(request);

        try {
            orderService.updateStatus(orderID, status);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to update order status!");
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);

    }
}
