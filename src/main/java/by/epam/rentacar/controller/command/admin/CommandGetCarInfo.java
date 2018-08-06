package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.CarInfoDTO;
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

public class CommandGetCarInfo implements Command {

    private static final Logger logger = LogManager.getLogger(CommandGetCarInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));

        AdminService adminService = ServiceFactory.getInstance().getAdminService();
        CarInfoDTO carInfoDTO = null;

        try {
            carInfoDTO = adminService.getCarInfo(carID);

            request.setAttribute(RequestAttributes.KEY_CAR_INFO, carInfoDTO);
            request.getRequestDispatcher(PageParameters.PAGE_ADMIN_CAR).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to get car info!", e);
        }

    }
}
