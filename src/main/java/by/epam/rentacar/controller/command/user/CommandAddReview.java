package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.domain.dto.AddReviewDTO;
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

public class CommandAddReview implements Command {

    private static final Logger logger = LogManager.getLogger(CommandAddReview.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        AddReviewDTO addReviewDTO = parseRequest(request);

        try {
            ServiceFactory.getInstance().getReviewService().addReview(addReviewDTO);

            String page = request.getHeader(RequestHeader.KEY_REFERER);
            response.sendRedirect(page);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to add review!", e);
        }

    }

    private AddReviewDTO parseRequest(HttpServletRequest request) {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        int userID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_USER));
        String reviewText = request.getParameter(RequestParameters.KEY_REVIEW_TEXT);

        AddReviewDTO addReviewDTO = new AddReviewDTO();
        addReviewDTO.setCarID(carID);
        addReviewDTO.setUserID(userID);
        addReviewDTO.setReviewText(reviewText);

        return addReviewDTO;

    }
}
