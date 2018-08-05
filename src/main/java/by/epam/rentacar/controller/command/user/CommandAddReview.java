package by.epam.rentacar.controller.command.user;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.domain.dto.AddReviewDTO;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.controller.util.constant.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandAddReview implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int carID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_CAR));
        int userID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_USER));
        String reviewText = request.getParameter(RequestParameters.KEY_REVIEW_TEXT);

        AddReviewDTO addReviewDTO = new AddReviewDTO();
        addReviewDTO.setCarID(carID);
        addReviewDTO.setUserID(userID);
        addReviewDTO.setReviewText(reviewText);

        ServiceFactory.getInstance().getReviewService().addReview(addReviewDTO);

        String page = request.getHeader(RequestHeader.KEY_REFERER);
        response.sendRedirect(page);

    }
}
