package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.UserOrdersDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetOrders implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int userID = ((User)request.getSession().getAttribute("user")).getId();

        UserOrdersDTO userOrdersDTO = new UserOrdersDTO();

        OrderService orderService = new OrderServiceImpl();

        try {
            userOrdersDTO  = orderService.getUserOrders(userID);
            request.setAttribute("orders_info", userOrdersDTO);
            request.getRequestDispatcher("/user/orders").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
