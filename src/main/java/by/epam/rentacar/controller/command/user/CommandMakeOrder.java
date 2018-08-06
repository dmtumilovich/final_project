package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
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
import java.io.IOException;
import java.text.*;
import java.util.Date;

public class CommandMakeOrder implements Command {

    private static final Logger logger = LogManager.getLogger(CommandMakeOrder.class);

    private static final String PAGE_ORDERS = "/controller?command=orders";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        MakeOrderDTO makeOrderDTO = parseRequest(request);
        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            orderService.makeOrder(makeOrderDTO);
            response.sendRedirect(PAGE_ORDERS);
        } catch (InvalidDateRangeException e) {
            String page = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(page);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to make order!", e);
        }

    }

    private MakeOrderDTO parseRequest(HttpServletRequest request) {

        int userID = ((User) request.getSession().getAttribute(SessionAttributes.KEY_USER)).getId();
        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        String dateStart = request.getParameter(RequestParameters.KEY_DATE_START);
        String dateEnd = request.getParameter(RequestParameters.KEY_DATE_END);

        MakeOrderDTO makeOrderDTO = new MakeOrderDTO();
        makeOrderDTO.setUserID(userID);
        makeOrderDTO.setCarID(carID);
        makeOrderDTO.setDateStart(dateStart);
        makeOrderDTO.setDateEnd(dateEnd);

        return makeOrderDTO;

    }
}
