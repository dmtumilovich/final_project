package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.dto.UserOrderDTO;
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
import java.util.List;

public class CommandGetOrders extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandGetOrders.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);
        String pageStr = request.getParameter(RequestParameters.KEY_PAGE);
        int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);

        OrderService orderService = new OrderServiceImpl();

        try {
            List<UserOrderDTO> userOrders = orderService.getUserOrders(userID, page, 10);
            int pagesCount = orderService.getUserOrdersPagesCount(userID, 10);

            request.setAttribute(RequestAttributes.KEY_PAGE, page);
            request.setAttribute(RequestAttributes.KEY_PAGE_COUNT, pagesCount);
            request.setAttribute(RequestAttributes.KEY_ORDER_LIST, userOrders);
            request.getRequestDispatcher(PageParameters.PAGE_ORDERS).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get user orders!", e);
        }

    }
}
