package by.epam.rentacar.controller;

import by.epam.rentacar.controller.command.Command;
import by.epam.rentacar.controller.command.CommandContainer;
import by.epam.rentacar.controller.command.admin.CommandConfirmOrder;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ImageUpload", urlPatterns = "/upload")
@MultipartConfig(
        fileSizeThreshold = 1024*1024*2 ,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*5
)
public class ImageUploadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandStr = request.getParameter(RequestParameters.KEY_COMMAND);
        Command command = CommandContainer.get(commandStr);
        command.execute(request, response);

    }
}
