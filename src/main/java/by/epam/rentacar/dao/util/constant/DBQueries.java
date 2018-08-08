package by.epam.rentacar.dao.util.constant;

public class DBQueries {

    //user queries
    public static final String FIND_USER_BY_ID = "SELECT user_list.id_user, user_list.username, user_list.password, user_list.email, user_list.name, user_list.surname, user_list.phone_number, user_list.passport, user_list.photo, user_role.role FROM user_list INNER JOIN user_role ON user_list.id_role = user_role.id_role WHERE id_user = ?";
    public static final String FIND_USER_BY_USERNAME = "SELECT user_list.id_user, user_list.username, user_list.password, user_list.email, user_list.name, user_list.surname, user_list.phone_number, user_list.passport, user_list.photo, user_role.role FROM user_list INNER JOIN user_role ON user_list.id_role = user_role.id_role WHERE username = ?";
    public static final String ADD_USER = "INSERT INTO user_list (username, password, email, id_role) VALUES (?, ?, ?, '2')";
    public static final String UPDATE_USER_INFO = "UPDATE user_list SET name = ?, surname = ?, phone_number = ?, passport = ? WHERE id_user = ?";
    public static final String FIND_PASSWORD_BY_USERNAME = "SELECT password FROM user_list WHERE username = ?";
    public static final String UPDATE_PASSWORD = "UPDATE user_list SET password = ? WHERE id_user = ?";

    //car queries
    public static final String FIND_ALL_CARS = "SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_available, price, car_photos.id_photo, car_photos.photo_url\n" +
                                                "FROM car_list\n" +
                                                "LEFT JOIN car_photos\n" +
                                                "ON car_list.id_car = car_photos.id_car";

    public static final String FIND_CAR_BY_ID = "SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_available, price, car_photos.id_photo, car_photos.photo_url\n" +
                                                "FROM car_list\n" +
                                                "LEFT JOIN car_photos\n" +
                                                "ON car_list.id_car = car_photos.id_car\n" +
                                                "WHERE car_list.id_car = ?";

    public static final String FIND_CAR_WITH_REVIEWS_BY_ID = "SELECT car_list.*, car_review.id_review, car_review.id_user, user_list.username, user_list.photo, car_review.review, car_review.time\n" +
                                                            "FROM car_list\n" +
                                                            "LEFT JOIN car_review ON car_list.id_car = car_review.id_car\n" +
                                                            "LEFT JOIN user_list ON car_review.id_user = user_list.id_user\n" +
                                                            "WHERE car_list.id_car = ?\n" +
                                                            "ORDER BY time DESC";

    public static final String FIND_ALL_CARS_FOR_ADMIN = "SELECT car_list.id_car, car_list.brand, car_list.model, car_list.class, car_list.price, car_list.is_available, COUNT(car_review.id_car) AS comments_count\n" +
                                                        "FROM car_list\n" +
                                                        "LEFT JOIN car_review ON car_list.id_car = car_review.id_car\n" +
                                                        "GROUP BY car_list.id_car"; // rename?

    public static final String INSERT_CAR = "INSERT INTO car_list (brand, model, class, color, year_of_issue, number_of_seats, engine_volume, is_available, price) VALUES (?, ? ,?, ?, ?, ?, ?, '1', ?)"; //убрать единичку
    public static final String UPDATE_CAR = "UPDATE car_list\n" +
                                            "SET brand = ?, model = ?, class = ?, year_of_issue = ?, number_of_seats = ?, color = ?, engine_volume = ?, is_available = ?, price = ?\n" +
                                            "WHERE id_car = ?";

    public static final String GET_CAR_PRICE_BY_ID = "SELECT price\n" +
                                                    "FROM car_list\n" +
                                                    "WHERE id_car = ?";

    //orders queries
    public static final String GET_ALL_ORDERS = "SELECT order_list.id_order, id_user, id_car, date_start, date_end, total_price, order_status.status\n" +
                                                "FROM order_list\n" +
                                                "INNER JOIN order_status\n" +
                                                "ON order_list.id_status = order_status.id_status\n" +
                                                "ORDER BY date_start DESC";

    public static final String GET_USER_ORDER = "SELECT order_list.*, order_status.status, car_list.*\n" +
                                                "FROM order_list\n" +
                                                "INNER JOIN car_list\n" +
                                                "ON order_list.id_car = car_list.id_car\n" +
                                                "INNER JOIN order_status\n" +
                                                "ON order_list.id_status = order_status.id_status\n" +
                                                "WHERE id_order = ?";

    public static final String GET_USER_ORDERS = "SELECT order_list.*, order_status.status, car_list.*\n" +
                                                "FROM order_list\n" +
                                                "INNER JOIN car_list\n" +
                                                "ON order_list.id_car = car_list.id_car\n" +
                                                "INNER JOIN order_status\n" +
                                                "ON order_list.id_status = order_status.id_status\n" +
                                                "WHERE id_user = ?";

    public static final String GET_ORDER_INFO_FOR_ADMIN = "SELECT order_list.id_order, order_list.id_user, order_list.id_car, order_list.date_start, order_list.date_end, order_list.total_price,\n" +
                                                        "order_status.status,\n" +
                                                        "user_list.username, user_list.name, user_list.surname, user_list.phone_number,\n" +
                                                        "car_list.brand, car_list.model, car_list.class, car_list.price\n" +
                                                        "FROM order_list\n" +
                                                        "INNER JOIN order_status\n" +
                                                        "ON order_list.id_status = order_status.id_status\n" +
                                                        "INNER JOIN user_list\n" +
                                                        "ON order_list.id_user = user_list.id_user\n" +
                                                        "INNER JOIN car_list\n" +
                                                        "ON order_list.id_car = car_list.id_car\n" +
                                                        "WHERE id_order = ?";

    public static final String CONFIRM_ORDER = "UPDATE order_list\n" +
                                                "SET id_status = ?\n" +
                                                "WHERE id_order = ?";

    public static final String REJECT_ORDER = "UPDATE order_list\n" +
                                                "SET id_status = ?\n" +
                                                "WHERE id_order = ?";

    public static final String INSERT_ORDER = "INSERT INTO order_list (id_user, id_car, date_start, date_end, total_price, id_status)\n" +
                                            "VALUES (?, ?, ?, ?, ?, ?)";

    //review orders

    public static final String INSERT_REVIEW = "INSERT INTO car_review (id_car, id_user, review, time)\n" +
                                                "VALUES (?, ?, ?, ?)";

    private DBQueries() {

    }
}
