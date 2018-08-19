package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.PathHelper;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The command for changing locale.
 */
public class CommandLanguage implements Command{

    /**
     * Gets local parameter from the request and puts it to the session as an attribute with name {@link SessionAttributes#KEY_LOCAL}.
     * The redirects to the previous page, if it not null. Otherwise redirects to the main page.
     *
     * @param request
     *          an {@link HttpServletRequest} object that contains client request
     * @param response
     *          an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession(true).setAttribute(SessionAttributes.KEY_LOCAL, request.getParameter(RequestParameters.KEY_LOCAL));

        String page = PathHelper.getPreviousPage(request);
        response.sendRedirect(page);
    }
}
