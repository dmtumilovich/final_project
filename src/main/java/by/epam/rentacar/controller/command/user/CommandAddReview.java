package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.SessionAttributes;
import by.epam.rentacar.domain.entity.Review;
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

public class CommandAddReview extends UserCommand {

    private static final Logger logger = LogManager.getLogger(CommandAddReview.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(!identifyUser(request)) {
            response.sendRedirect(PageParameters.PAGE_SIGNIN);
            return;
        }

        Review review = parseRequest(request);

        try {
            ServiceFactory.getInstance().getReviewService().addReview(review);

            String page = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(page);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to add review!", e);
        }

    }

    private Review parseRequest(HttpServletRequest request) {

        int userID = (int) request.getSession().getAttribute(SessionAttributes.KEY_ID_USER);
        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        String reviewText = request.getParameter(RequestParameters.KEY_REVIEW_TEXT);

        Review review = new Review();
        review.setCarID(carID);
        review.setUserID(userID);
        review.setReviewText(reviewText);

        return review;

    }
}
