package by.epam.rentacar.controller.command.user;


import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.domain.entity.User;
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

public class CommandGetBookingInfo implements Command {

    private static final Logger logger = LogManager.getLogger(CommandGetBookingInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);
        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));

        OrderingInfo orderingInfo = null;
        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            orderingInfo = orderService.getOrderingInfo(carID, userID);

            request.setAttribute(RequestAttributes.KEY_BOOKING_INFO, orderingInfo);
            request.getRequestDispatcher(PageParameters.PAGE_ORDERING).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get booking info!", e);
        }

    }
}
