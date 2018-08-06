package by.epam.rentacar.controller.command.admin;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestParameters;
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

public class CommandDeleteCar implements Command {

    private static final Logger logger = LogManager.getLogger(CommandDeleteCar.class);

    private static final String PAGE_CARS = "/controller?command=show_car_table"; //rename

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));

        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        try {
            adminService.deleteCar(carID);

            response.sendRedirect(PAGE_CARS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to delete car!", e);
        }

    }

}
