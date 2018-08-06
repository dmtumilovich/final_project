package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetOrderInfo implements Command {

    private static final Logger logger = LogManager.getLogger(CommandGetCarInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int orderID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_ORDER));

        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        OrderInfoDTO orderInfo = null;

        try {
            orderInfo = adminService.getOrderInfo(orderID);

            request.setAttribute(RequestAttributes.KEY_ORDER_INFO, orderInfo);
            request.getRequestDispatcher(PageParameters.PAGE_ADMIN_ORDER).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get order info!", e);
        }

    }
}
