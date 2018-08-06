package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandDeleteReview implements Command {

    private static final Logger logger = LogManager.getLogger(CommandDeleteReview.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int reviewID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_REVIEW));

        try {
            ServiceFactory.getInstance().getReviewService().deleteReview(reviewID);

            String page = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(page);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to delete review by user!", e);
        }
    }
}
