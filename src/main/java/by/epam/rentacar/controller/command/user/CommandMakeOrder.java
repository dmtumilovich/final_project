package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.dto.MakeOrderDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.*;
import java.util.Date;

public class CommandMakeOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int userID = ((User) request.getSession().getAttribute(SessionAttributes.KEY_USER)).getId();
        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        String dateStart = request.getParameter("date_start");
        String dateEnd = request.getParameter("date_end");

        MakeOrderDTO makeOrderDTO = new MakeOrderDTO();
        makeOrderDTO.setUserID(userID);
        makeOrderDTO.setCarID(carID);
        makeOrderDTO.setDateStart(dateStart);
        makeOrderDTO.setDateEnd(dateEnd);

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            orderService.makeOrder(makeOrderDTO);
            response.sendRedirect("/controller?command=orders");
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
