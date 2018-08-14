package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
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

public class CommandGetOrderInfo extends AdminCommand {

    private static final Logger logger = LogManager.getLogger(CommandGetCarInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyAdmin(request)) {
            response.sendRedirect(PageParameters.PAGE_MAIN);
            return;
        }

        int orderID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_ORDER));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        OrderInfoDTO orderInfo = null;

        try {
            orderInfo = orderService.getOrderInfo(orderID);

            request.setAttribute(RequestAttributes.KEY_ORDER_INFO, orderInfo);
            request.getRequestDispatcher(PageParameters.PAGE_ADMIN_ORDER).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get order info!", e);
        }

    }
}
