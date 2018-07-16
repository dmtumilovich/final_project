package by.epam.rentacar.controller.command;

import by.epam.rentacar.dto.AddReviewDTO;
import by.epam.rentacar.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandAddReview implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int carID = Integer.parseInt(request.getParameter("car_id"));
        int userID = Integer.parseInt(request.getParameter("user_id"));
        String reviewText = request.getParameter("review_text");

        AddReviewDTO addReviewDTO = new AddReviewDTO();
        addReviewDTO.setCarID(carID);
        addReviewDTO.setUserID(userID);
        addReviewDTO.setReviewText(reviewText);

        ServiceFactory.getInstance().getReviewService().addReview(addReviewDTO);

        //переделать
        response.sendRedirect("/controller?command=show_selected_car&car_id=" + carID);

    }
}
