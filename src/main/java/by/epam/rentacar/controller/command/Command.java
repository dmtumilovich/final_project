package by.epam.rentacar.controller.command;

import javax.servlet.http.HttpServletRequest;

//rename this class
public interface Command {
    String execute(HttpServletRequest request);
}
