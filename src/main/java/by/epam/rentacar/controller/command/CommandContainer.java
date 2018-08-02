package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.command.admin.*;

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
        commands.put("change_password", new CommandChangePassword());
        commands.put("show_car_table", new CommandGetCarTable());
        commands.put("get_car_info", new CommandGetCarInfo());
        commands.put("edit_car", new CommandEditCar());
        commands.put("delete_car", new CommandDeleteCar());
        commands.put("add_car", new CommandAddCar());
        commands.put("get_orders", new CommandGetOrdersTable());
        commands.put("get_order_info", new CommandGetOrderInfo());
        commands.put("confirm_order", new CommandConfirmOrder());
        commands.put("reject_order", new CommandRejectOrder());
    }

    public static Command get(String commandStr) {
        return commands.get(commandStr);
    }

    private CommandContainer() {

    }

}
