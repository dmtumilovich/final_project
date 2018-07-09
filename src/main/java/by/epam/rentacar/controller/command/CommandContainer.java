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
    }

    public static Command get(String commandStr) {
        return commands.get(commandStr);
    }

    private CommandContainer() {

    }

}
