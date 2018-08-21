package by.epam.rentacar.dao.util.constant;

/**
 * The class defines {@link String} constants containing SQL queries.
 */
public class DBQueries {

    //user queries
    public static final String FIND_USER_BY_ID = "SELECT user_list.id_user, user_list.username, user_list.password, user_list.email, user_list.name, user_list.surname, user_list.phone_number, user_list.passport, user_list.photo, user_role.role FROM user_list INNER JOIN user_role ON user_list.id_role = user_role.id_role WHERE id_user = ?";
    public static final String FIND_USER_BY_USERNAME = "SELECT user_list.id_user, user_list.username, user_list.password, user_list.email, user_list.name, user_list.surname, user_list.phone_number, user_list.passport, user_list.photo, user_role.role FROM user_list INNER JOIN user_role ON user_list.id_role = user_role.id_role WHERE username = ?";
    public static final String FIND_USER_BY_USERNAME_AND_PASSWORD = "SELECT user_list.id_user, user_list.username, user_list.password, user_list.email, user_list.name, user_list.surname, user_list.phone_number, user_list.passport, user_list.photo, user_role.role FROM user_list INNER JOIN user_role ON user_list.id_role = user_role.id_role WHERE username = ? AND password = ?";
    public static final String FIND_USER_ID_BY_USERNAME = "SELECT id_user FROM user_list WHERE username = ?";
    public static final String ADD_USER = "INSERT INTO user_list (username, password, email, id_role, photo) VALUES (?, ?, ?, '2', 'no_avatar.png')";
    public static final String UPDATE_USER_INFO = "UPDATE user_list SET name = ?, surname = ?, phone_number = ?, passport = ? WHERE id_user = ?";
    public static final String FIND_PASSWORD_BY_USERNAME = "SELECT password FROM user_list WHERE username = ?";
    public static final String UPDATE_PASSWORD = "UPDATE user_list SET password = ? WHERE id_user = ?";
    public static final String SET_USER_PHOTO = "UPDATE user_list\n" +
                                                "SET photo = ?\n" +
                                                "WHERE id_user = ?";
    public static final String GET_ID_BY_USERNAME = "SELECT id_user FROM user_list WHERE username = ?";
    public static final String GET_ID_BY_EMAIL = "SELECT id_user FROM user_list WHERE email = ?";

    public static final String IS_CORRECT_PASSWORD = "SELECT id_user\n" +
                                                    "FROM user_list\n" +
                                                    "WHERE id_user = ? AND password = ?";

    //car queries

    public static final String FIND_ALL_NOT_DELETED_CARS = "SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_deleted, price, photos.id_photo, photos.photo_url\n" +
                                                            "FROM car_list\n" +
                                                            "LEFT JOIN (SELECT id_photo, id_car, photo_url\n" +
                                                                        "FROM car_photos\n" +
                                                                        "WHERE id_photo IN (SELECT MIN(id_photo)\n" +
                                                                                            "FROM car_photos\n" +
                                                                                            "GROUP BY id_car)\n" +
                                                                                            ") photos\n" +
                                                            "ON car_list.id_car = photos.id_car\n" +
                                                            "WHERE is_deleted = '0'\n" +
                                                            "GROUP BY car_list.id_car, photos.id_photo LIMIT ? OFFSET ?";

    public static final String FIND_ALL_NOT_DELETED_CARS_BY_DATE_RANGE = "SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_deleted, price, photos.id_photo, photos.photo_url\n" +
                                                                        "FROM car_list\n" +
                                                                        "LEFT JOIN (SELECT id_photo, id_car, photo_url\n" +
                                                                                    "FROM car_photos\n" +
                                                                                    "WHERE id_photo IN (SELECT MIN(id_photo)\n" +
                                                                                                        "FROM car_photos\n" +
                                                                                                        "GROUP BY id_car)\n" +
                                                                                                        ") photos\n" +
                                                                        "ON car_list.id_car = photos.id_car\n" +
                                                                        "WHERE is_deleted = '0' AND car_list.id_car NOT IN (SELECT DISTINCT order_list.id_car \n" +
                                                                                                                            "FROM order_list\n" +
                                                                                                                            "WHERE ((? BETWEEN date_start AND date_end) \n" +
                                                                                                                            "OR (date_end BETWEEN ? AND ?))\n" +
                                                                                                                            "AND (id_status = '1' or id_status = '4')\n" +
                                                                                                                            ")\n" +
                                                                        "GROUP BY car_list.id_car, photos.id_photo LIMIT ? OFFSET ?";

    public static final String FIND_ALL_NOT_DELETED_CARS_BY_DATE_RANGE_AND_CLASS = "SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_deleted, price, photos.id_photo, photos.photo_url\n" +
                                                                                    "FROM car_list\n" +
                                                                                    "LEFT JOIN (SELECT id_photo, id_car, photo_url\n" +
                                                                                    "FROM car_photos\n" +
                                                                                    "WHERE id_photo IN (SELECT MIN(id_photo)\n" +
                                                                                                        "FROM car_photos\n" +
                                                                                                        "GROUP BY id_car)\n" +
                                                                                                        ") photos\n" +
                                                                                    "ON car_list.id_car = photos.id_car\n" +
                                                                                    "WHERE is_deleted = '0' AND class = ? AND car_list.id_car NOT IN (SELECT DISTINCT order_list.id_car \n" +
                                                                                                                                                            "FROM order_list\n" +
                                                                                                                                                            "WHERE ((? BETWEEN date_start AND date_end) \n" +
                                                                                                                                                            "OR (date_end BETWEEN ? AND ?))\n" +
                                                                                                                                                            "AND (id_status = '1' or id_status = '4')\n" +
                                                                                                                                                            ")\n" +
                                                                                    "GROUP BY car_list.id_car, photos.id_photo LIMIT ? OFFSET ?";

    public static final String GET_COUNT_OF_NOT_DELETED_CARS = "SELECT COUNT(id_car) AS cars_count\n" +
                                                                "FROM car_list\n" +
                                                                "WHERE is_deleted = '0'";

    public static final String GET_COUNT_OF_NOT_DELETED_CARS_BY_DATE_RANGE = "SELECT COUNT(car_list.id_car) AS cars_count\n" +
                                                                            "FROM car_list\n" +
                                                                            "WHERE is_deleted = '0' AND car_list.id_car NOT IN (SELECT DISTINCT order_list.id_car \n" +
                                                                            "FROM order_list\n" +
                                                                            "WHERE ((? BETWEEN date_start AND date_end) \n" +
                                                                            "OR (date_end BETWEEN ? AND ?))\n" +
                                                                            "AND (id_status = '1' or id_status = '4')\n" +
                                                                            ")";

    public static final String GET_COUNT_OF_NOT_DELETED_CARS_BY_DATE_RANGE_AND_CLASS = "SELECT COUNT(car_list.id_car) AS cars_count\n" +
                                                                                        "FROM car_list\n" +
                                                                                        "WHERE is_deleted = '0' AND class = ? AND car_list.id_car NOT IN (SELECT DISTINCT order_list.id_car \n" +
                                                                                        "FROM order_list\n" +
                                                                                        "WHERE ((? BETWEEN date_start AND date_end) \n" +
                                                                                        "OR (date_end BETWEEN ? AND ?))\n" +
                                                                                        "AND (id_status = '1' or id_status = '4')\n" +
                                                                                        ");";

    public static final String FIND_CAR_BY_ID = "SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_deleted, price, car_photos.id_photo, car_photos.photo_url\n" +
                                                "FROM car_list\n" +
                                                "LEFT JOIN car_photos\n" +
                                                "ON car_list.id_car = car_photos.id_car\n" +
                                                "WHERE car_list.id_car = ?";

    public static final String INSERT_CAR = "INSERT INTO car_list (brand, model, class, color, year_of_issue, number_of_seats, engine_volume, is_deleted, price) VALUES (?, ? ,?, ?, ?, ?, ?, '0', ?)"; //убрать единичку
    public static final String UPDATE_CAR = "UPDATE car_list\n" +
                                            "SET brand = ?, model = ?, class = ?, year_of_issue = ?, number_of_seats = ?, color = ?, engine_volume = ?, price = ?\n" +
                                            "WHERE id_car = ?";

    public static final String DELETE_CAR = "UPDATE car_list\n" +
                                            "SET is_deleted = '1'\n" +
                                            "WHERE id_car = ?";

    public static final String GET_CAR_PRICE_BY_ID = "SELECT price\n" +
                                                    "FROM car_list\n" +
                                                    "WHERE id_car = ?";

    public static final String ADD_CAR_PHOTO = "INSERT INTO car_photos (id_car, photo_url)\n" +
                                                "VALUES (?, ?)";

    public static final String DELETE_CAR_PHOTO = "DELETE FROM car_photos WHERE id_photo = ?";

    public static final String IS_CAR_AVAILABLE = "SELECT id_order\n" +
                                                    "FROM order_list\n" +
                                                    "WHERE id_car = ?\n" +
                                                    "AND ((date_end BETWEEN ? AND ?) OR (? BETWEEN date_start AND date_end))\n" +
                                                    "AND (id_status =  '1' OR id_status = '4')";

    //orders queries

    public static final String GET_ORDER_BY_ID = "SELECT order_list.*, order_status.status\n" +
                                                "FROM order_list\n" +
                                                "INNER JOIN order_status\n" +
                                                "ON order_list.id_status = order_status.id_status\n" +
                                                "WHERE id_order = ?";

    public static final String GET_ALL_ORDERS = "SELECT order_list.id_order, id_user, id_car, date_start, date_end, total_price, comment, order_status.status\n" +
                                                "FROM order_list\n" +
                                                "INNER JOIN order_status\n" +
                                                "ON order_list.id_status = order_status.id_status\n" +
                                                "ORDER BY date_start DESC LIMIT ? OFFSET ?";

    public static final String GET_ORDERS_BY_STATUS_ID = "SELECT order_list.id_order, id_user, id_car, date_start, date_end, total_price, comment, order_status.status\n" +
                                                        "FROM order_list\n" +
                                                        "INNER JOIN order_status\n" +
                                                        "ON order_list.id_status = order_status.id_status\n" +
                                                        "WHERE order_list.id_status = ?\n" +
                                                        "ORDER BY date_start DESC LIMIT ? OFFSET ?";

    public static final String GET_COUNT_OF_ALL_ORDERS = "SELECT COUNT(id_order) AS orders_count\n" +
                                                        "FROM order_list";

    public static final String GET_COUNT_OF_ALL_ORDERS_BY_STATUS_ID = "SELECT COUNT(id_order) AS orders_count\n" +
                                                                        "FROM order_list\n" +
                                                                        "WHERE id_status = ?;";

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
                                                "WHERE id_user = ?\n" +
                                                "ORDER BY date_start DESC\n" +
                                                "LIMIT ? OFFSET ?";

    public static final String GET_COUNT_OF_USER_ORDERS = "SELECT COUNT(id_order) AS orders_count\n" +
                                                            "FROM order_list\n" +
                                                            "WHERE id_user = ?";

    public static final String GET_ORDER_INFO_FOR_ADMIN = "SELECT order_list.id_order, order_list.id_user, order_list.id_car, order_list.date_start, order_list.date_end, order_list.total_price, order_list.comment,\n" +
                                                        "order_status.status,\n" +
                                                        "user_list.*, role,\n" +
                                                        "car_list.*\n" +
                                                        "FROM order_list\n" +
                                                        "INNER JOIN order_status\n" +
                                                        "ON order_list.id_status = order_status.id_status\n" +
                                                        "INNER JOIN user_list\n" +
                                                        "ON order_list.id_user = user_list.id_user\n" +
                                                        "INNER JOIN user_role\n" +
                                                        "ON user_list.id_role = user_role.id_role\n" +
                                                        "INNER JOIN car_list\n" +
                                                        "ON order_list.id_car = car_list.id_car\n" +
                                                        "WHERE id_order = ?";

    public static final String INSERT_ORDER = "INSERT INTO order_list (id_user, id_car, date_start, date_end, total_price, id_status)\n" +
                                            "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String GET_STATUS_ID_BY_NAME = "SELECT id_status FROM order_status WHERE status = ?";

    public static final String UPDATE_ORDER_STATUS = "UPDATE order_list\n" +
                                                    "SET id_status = ?\n" +
                                                    "WHERE id_order = ?";

    public static final String UPDATE_ORDER_STATUS_WITH_COMMENT = "UPDATE order_list\n" +
                                                                    "SET id_status = ?, comment = ?\n" +
                                                                    "WHERE id_order = ?";

    public static final String REJECT_ORDERS_INTERSECTING_DATE_RANGE = "UPDATE order_list\n" +
                                                                        "SET id_status = '2'\n" +
                                                                        "WHERE id_order IN (SELECT id_order FROM (SELECT * FROM order_list) AS ol\n" +
                                                                        "WHERE ol.id_car = ? AND ol.id_order != ? AND id_status = '3'\n" +
                                                                        "AND (? BETWEEN ol.date_start AND ol.date_end\n" +
                                                                        "OR ol.date_end BETWEEN ? AND ?)\n" +
                                                                        ")";

    //review queries

    public static final String INSERT_REVIEW = "INSERT INTO car_review (id_car, id_user, review, time, is_deleted)\n" +
                                                "VALUES (?, ?, ?, ?, '0')";

    public static final String DELETE_REVIEW = "UPDATE car_review\n" +
                                                "SET is_deleted = ?\n" +
                                                "WHERE id_review = ?";

    public static final String GET_ALL_REVIEWS_BY_CAR_ID = "SELECT id_review, car_review.id_user, review, time, user_list.username, user_list.photo\n" +
                                                            "FROM car_review\n" +
                                                            "LEFT JOIN user_list\n" +
                                                            "ON car_review.id_user = user_list.id_user\n" +
                                                            "WHERE car_review.id_car = ? AND is_deleted != ?\n" +
                                                            "ORDER BY time DESC";

    private DBQueries() {

    }
}
