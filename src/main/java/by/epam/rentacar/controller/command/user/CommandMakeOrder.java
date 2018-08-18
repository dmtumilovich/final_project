package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.dto.MakeOrderDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.InvalidDateRangeException;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.*;
import java.util.Date;

public class CommandMakeOrder extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandMakeOrder.class);

    private static final String PAGE_ORDERS = "/controller?command=orders";
    private static final String MESSAGE_INVALID_DATE_RANGE = "local.ordering.error.invalid-date-range";
    private static final String MESSAGE_ORDER_WAS_MADE = "local.user-orders.success.order-was-made";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        MakeOrderDTO makeOrderDTO = parseRequest(request);
        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        String destPage = PAGE_ORDERS;
        HttpSession session = request.getSession();

        try {
            orderService.makeOrder(makeOrderDTO);
            session.setAttribute(SessionAttributes.KEY_SUCCESS_MESSAGE, MESSAGE_ORDER_WAS_MADE);
        } catch (InvalidDateRangeException e) {
            session.setAttribute(SessionAttributes.KEY_ERROR_MESSAGE, MESSAGE_INVALID_DATE_RANGE);
            destPage = PathHelper.getRefererPage(request);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to make order!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);

    }

    private MakeOrderDTO parseRequest(HttpServletRequest request) {

        HttpSession session = request.getSession();

        int userID = (int) session.getAttribute(SessionAttributes.KEY_ID_USER);
        String dateStart = (String) session.getAttribute("date_start");
        String dateEnd = (String) session.getAttribute("date_end");
        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));

        logger.log(Level.DEBUG, "Date start: " + dateStart);
        logger.log(Level.DEBUG, "Date end: " + dateEnd);

        MakeOrderDTO makeOrderDTO = new MakeOrderDTO();
        makeOrderDTO.setUserID(userID);
        makeOrderDTO.setCarID(carID);
        makeOrderDTO.setDateStart(dateStart);
        makeOrderDTO.setDateEnd(dateEnd);

        return makeOrderDTO;

    }
}
