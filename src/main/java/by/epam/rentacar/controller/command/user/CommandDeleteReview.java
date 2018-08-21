package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.PageParameters;
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

/**
 * The command for deleting review.
 */
public class CommandDeleteReview extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandDeleteReview.class);

    /**
     * Gets an review id and then processes its by service layer.
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

        int reviewID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_REVIEW));
        String destPage = PathHelper.getPreviousPage(request);

        try {
            ServiceFactory.getInstance().getReviewService().deleteReview(reviewID);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to delete review by user!", e);
            destPage = PageParameters.PAGE_ERROR;
        }

        response.sendRedirect(destPage);
    }
}
