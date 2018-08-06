package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.dto.UserOrdersDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetOrders implements Command {

    private static final Logger logger = LogManager.getLogger(CommandGetOrders.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int userID = ((User)request.getSession().getAttribute(SessionAttributes.KEY_USER)).getId();

        UserOrdersDTO userOrdersDTO = null;
        OrderService orderService = new OrderServiceImpl();

        try {
            userOrdersDTO  = orderService.getUserOrders(userID);
            request.setAttribute(RequestAttributes.KEY_ORDERS_INFO, userOrdersDTO);
            request.getRequestDispatcher(PageParameters.PAGE_ORDERS).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get user orders!", e);
        }

    }
}
