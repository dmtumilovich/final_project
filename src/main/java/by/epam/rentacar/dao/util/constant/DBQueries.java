package by.epam.rentacar.dao.util.constant;

public class DBQueries {

    //user queries
    public static final String FIND_USER_BY_ID = "SELECT user_list.id_user, user_list.username, user_list.password, user_list.email, user_list.name, user_list.surname, user_list.phone_number, user_list.passport, user_role.role FROM user_list INNER JOIN user_role ON user_list.id_role = user_role.id_role WHERE id_user = ?";
    public static final String FIND_USER_BY_USERNAME = "SELECT user_list.id_user, user_list.username, user_list.password, user_list.email, user_list.name, user_list.surname, user_list.phone_number, user_list.passport, user_role.role FROM user_list INNER JOIN user_role ON user_list.id_role = user_role.id_role WHERE username = ?";
    public static final String ADD_USER = "INSERT INTO user_list (username, password, email, id_role) VALUES (?, ?, ?, '2')";
    public static final String UPDATE_USER_INFO = "UPDATE user_list SET name = ?, surname = ?, phone_number = ?, passport = ? WHERE id_user = ?";

    //car queries
    public static final String FIND_ALL_CARS = "SELECT id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_available, price FROM car_list";
    public static final String FIND_CAR_BY_ID = "SELECT id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_available, price FROM car_list WHERE id_car = ?";
    public static final String FIND_CAR_WITH_REVIEWS_BY_ID = "SELECT car_list.*, car_review.id_review, car_review.id_user, user_list.username, car_review.review, car_review.time FROM car_list LEFT JOIN car_review ON car_list.id_car = car_review.id_car LEFT JOIN user_list ON car_review.id_user = user_list.id_user WHERE car_list.id_car = ? ORDER BY time DESC";

    private DBQueries() {

    }
}
