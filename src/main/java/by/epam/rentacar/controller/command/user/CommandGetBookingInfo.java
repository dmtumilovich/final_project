package by.epam.rentacar.controller.command.user;


import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The class for getting booking info for the user.
 */
public class CommandGetBookingInfo extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandGetBookingInfo.class);

    /**
     * Gets {@code userID}, {@code dateStart} and {@code dateEnd} from the session
     * and {@code carID} from the request, then processes them by service layer.
     * If data is valid puts {@link OrderingInfo} object as an attribute to the request
     * and redirects to the ordering page.
     * Otherwise redirects to the error page.
     *
     * @param request
     *          an {@link HttpServletRequest} object that contains client request
     * @param response
     *          an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);
        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));

        HttpSession session = request.getSession();
        String dateStart = (String) session.getAttribute(SessionAttributes.KEY_DATE_START);
        String dateEnd = (String) session.getAttribute(SessionAttributes.KEY_DATE_END);

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            OrderingInfo orderingInfo = orderService.getBookingInfo(carID, userID, dateStart, dateEnd);

            request.setAttribute(RequestAttributes.KEY_BOOKING_INFO, orderingInfo);
            request.getRequestDispatcher(PageParameters.PAGE_ORDERING).forward(request, response);
            return;

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get booking info!", e);
            response.sendRedirect(PageParameters.PAGE_ERROR);
        }

    }
}
