package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.UserOrderDTO;
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

public class CommandGetOrder extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandGetOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        int orderID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_ORDER));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {

            UserOrderDTO userOrderDTO = orderService.getUserOrder(orderID);
            request.setAttribute(RequestAttributes.KEY_ORDER_INFO, userOrderDTO);
            request.getRequestDispatcher(PageParameters.PAGE_ORDER).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get user order!", e);
            response.sendRedirect(PageParameters.PAGE_ERROR);
        }

    }
}
