package by.epam.rentacar.controller.command.factory;

import by.epam.rentacar.controller.command.*;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {


    public Command getCommand(HttpServletRequest request) {
        String command = request.getParameter("command");

        switch (command) {
            case "login":
                return new CommandLogin();
            case "logout":
                return new CommandLogout();
            case "registration":
                return new CommandSignup();
            case "change_lang":
                return new CommandLanguage();
        }

        return null;
    }

}
