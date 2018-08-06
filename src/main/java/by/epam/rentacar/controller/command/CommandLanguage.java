package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.constant.RequestHeader;
import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.controller.util.constant.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLanguage implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession(true).setAttribute(SessionAttributes.KEY_LOCAL, request.getParameter(RequestParameters.KEY_LOCAL));

        String page = request.getHeader(RequestHeader.KEY_REFERER);
        response.sendRedirect(page);
    }
}
