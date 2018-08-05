package by.epam.rentacar.controller.command.user;


import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//rename
public class CommandGetBookingInfo implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        int userID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_USER));

        OrderingInfo orderingInfo = null;

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            orderingInfo = orderService.getOrderingInfo(carID, userID);

            request.setAttribute("booking_info", orderingInfo);
            request.getRequestDispatcher("/user/ordering").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
