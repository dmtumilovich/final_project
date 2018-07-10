package by.epam.rentacar.controller;


import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.command.CommandContainer;
import by.epam.rentacar.util.constant.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandStr = request.getParameter(RequestParameters.KEY_COMMAND);
        Command command = CommandContainer.get(commandStr);
        command.execute(request, response);
    }
}
