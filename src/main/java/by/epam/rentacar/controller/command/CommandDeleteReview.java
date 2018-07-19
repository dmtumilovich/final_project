package by.epam.rentacar.controller.command;

import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.util.constant.PageParameters;
import by.epam.rentacar.util.constant.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandDeleteReview implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int reviewID = Integer.parseInt(request.getParameter(RequestParameters.KEY_ID_REVIEW));

        ServiceFactory.getInstance().getReviewService().deleteReview(reviewID);

        String page = request.getHeader("referer");
        response.sendRedirect(page);
    }
}
