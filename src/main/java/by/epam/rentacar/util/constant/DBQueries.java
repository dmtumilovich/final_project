package by.epam.rentacar.util.constant;

public class DBQueries {

    //user queries
    public static final String FIND_USER_BY_USERNAME = "SELECT id_user, username, password, email, name, surname, phone_number, passport, id_role FROM user_list WHERE username = ?";
    public static final String ADD_USER = "INSERT INTO user_list (username, password, email, id_role) VALUES (?, ?, ?, '2')";
    public static final String UPDATE_USER_INFO = "UPDATE user_list SET name = ?, surname = ?, phone_number = ?, passport = ? WHERE id_user = ?";

    //car queries
    public static final String FIND_ALL_CARS = "SELECT id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_available, price FROM car_list";
    public static final String FIND_CAR_BY_ID = "SELECT id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_available, price FROM car_list WHERE id_car = ?";

    private DBQueries() {

    }
}
