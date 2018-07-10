package by.epam.rentacar.controller.command;

import by.epam.rentacar.util.constant.PageParameters;
import by.epam.rentacar.util.constant.RequestParameters;
import by.epam.rentacar.util.constant.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLanguage implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession(true).setAttribute(SessionAttributes.KEY_LOCAL, request.getParameter(RequestParameters.KEY_LOCAL));
        response.sendRedirect(request.getContextPath() + PageParameters.PAGE_MAIN);
    }
}
