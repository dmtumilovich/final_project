package by.epam.rentacar.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("signin", new CommandSignin());
        commands.put("signup", new CommandSignup());
        commands.put("logout", new CommandLogout());
        commands.put("change_lang", new CommandLanguage());
        commands.put("edit_profile", new CommandEditProfile());
        commands.put("show_cars", new CommandShowCars());
        commands.put("show_selected_car", new CommandShowSelectedCar());
        commands.put("find_cars", new CommandFindCars());
        commands.put("add_review", new CommandAddReview());
        commands.put("delete_review", new CommandDeleteReview());
    }

    public static Command get(String commandStr) {
        return commands.get(commandStr);
    }

    private CommandContainer() {

    }

}
