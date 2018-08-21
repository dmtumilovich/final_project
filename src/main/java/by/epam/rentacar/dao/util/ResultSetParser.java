package by.epam.rentacar.dao.util;

import static by.epam.rentacar.dao.util.constant.DBSchema.*;

import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.domain.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * The class for parsing an {@link ResultSet} object. Defines static methods for it.
 */
public class ResultSetParser {

    /**
     * Parses an {@link ResultSet} object and creates {@link User} object.
     *
     * @param rs is an {@link ResultSet} object to parse.
     * @return an {@link User} object.
     * @throws SQLException
     */
    public static User createUser(ResultSet rs) throws SQLException {
        User user = null;

        int id = rs.getInt(UserListTable.ID_USER);
        String username = rs.getString(UserListTable.USERNAME);
        String email = rs.getString(UserListTable.EMAIL);
        String name = rs.getString(UserListTable.NAME);
        String surname = rs.getString(UserListTable.SURNAME);
        String phoneNumber = rs.getString(UserListTable.PHONE_NUMBER);
        String passport = rs.getString(UserListTable.PASSPORT);
        String role = rs.getString(UserRoleTable.ROLE_NAME);
        String photoUrl = rs.getString(UserListTable.PHOTO);

        user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhone(phoneNumber);
        user.setPassport(passport);
        user.setRole(User.Role.valueOf(role.toUpperCase()));
        user.setPhotoUrl(photoUrl);

        return user;
    }

    /**
     * Parses an {@link ResultSet} object and creates {@link Car} object.
     *
     * @param rs is an {@link ResultSet} object to parse.
     * @return an {@link Car} object.
     * @throws SQLException
     */
    public static Car createCar(ResultSet rs) throws SQLException {

        int id = rs.getInt(CarListTable.ID_CAR);
        String brand = rs.getString(CarListTable.BRAND);
        String model = rs.getString(CarListTable.MODEL);
        String carClass = rs.getString(CarListTable.CLASS);
        int yearOfIssue = rs.getInt(CarListTable.YEAR_OF_ISSUE);
        int numberOfSeats = rs.getInt(CarListTable.NUMBER_OF_SEATS);
        String color = rs.getString(CarListTable.COLOR);
        double engineVolume = rs.getDouble(CarListTable.ENGINE_VOLUME);
        boolean isAvailable = rs.getBoolean(CarListTable.IS_DELETED);
        double price = rs.getDouble(CarListTable.PRICE);

        Car car = new Car();
        car.setId(id);
        car.setBrand(brand);
        car.setModel(model);
        car.setCarClass(carClass);
        car.setYearOfIssue(yearOfIssue);
        car.setNumberOfSeats(numberOfSeats);
        car.setColor(color);
        car.setEngineVolume(engineVolume);
        car.setDeleted(isAvailable);
        car.setPrice(price);

        return car;
    }

    /**
     * Parses an {@link ResultSet} object and creates {@link CarPhoto} object.
     *
     * @param rs is an {@link ResultSet} object to parse.
     * @return an {@link CarPhoto} object.
     * @throws SQLException
     */
    public static CarPhoto createCarPhoto(ResultSet rs) throws SQLException {

        int id = rs.getInt(CarPhotosTable.ID_PHOTO);
        int carID = rs.getInt(CarPhotosTable.ID_CAR);
        String photoUrl = rs.getString(CarPhotosTable.PHOTO_URL);

        CarPhoto carPhoto = new CarPhoto();
        carPhoto.setId(id);
        carPhoto.setCarID(carID);
        carPhoto.setUrl(photoUrl);

        return carPhoto;

    }

    /**
     * Parses an {@link ResultSet} object and creates {@link Car} object
     * with list of {@link CarPhoto} objects.
     *
     * @param rs is an {@link ResultSet} object to parse.
     * @return an {@link Car} object.
     * @throws SQLException
     */
    public static Car createCarWithPhotos(ResultSet rs) throws SQLException {

        Car car = createCar(rs);

        do {
            CarPhoto photo = createCarPhoto(rs);
            if (photo.getUrl() != null) {
                car.addPhoto(photo);
            }
        } while (rs.next() && rs.getInt(CarListTable.ID_CAR) == car.getId());
        rs.previous();

        return car;

    }

    /**
     * Parses an {@link ResultSet} object and creates {@link Review} object.
     *
     * @param rs is an {@link ResultSet} object to parse.
     * @return an {@link Review} object.
     * @throws SQLException
     */
    public static Review createReview(ResultSet rs) throws SQLException {
        int reviewID = rs.getInt(CarReviewTable.ID_REVIEW);
        int userID = rs.getInt(CarReviewTable.ID_USER);
        String username = rs.getString(UserListTable.USERNAME);
        String userPhotoUrl = rs.getString(UserListTable.PHOTO);
        String reviewText = rs.getString(CarReviewTable.REVIEW_TEXT);
        Date date = new Date(rs.getTimestamp(CarReviewTable.DATE).getTime());

        Review review = new Review();
        review.setId(reviewID);
        review.setUsername(username);
        review.setUserPhotoUrl(userPhotoUrl);
        review.setUserID(userID);
        review.setReviewText(reviewText);
        review.setReviewDate(date);

        return review;
    }

    /**
     * Parses an {@link ResultSet} object and creates {@link Order} object.
     *
     * @param rs is an {@link ResultSet} object to parse.
     * @return an {@link Order} object.
     * @throws SQLException
     */
    public static Order createOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt(OrderListTable.ID_ORDER);
        int userID = rs.getInt(OrderListTable.ID_USER);
        int carID = rs.getInt(OrderListTable.ID_CAR);
        Date dateStart = new Date(rs.getTimestamp(OrderListTable.DATE_START).getTime());
        Date dateEnd = new Date(rs.getTimestamp(OrderListTable.DATE_END).getTime());
        double price = rs.getDouble(OrderListTable.TOTAL_PRICE);
        String statusStr= rs.getString(OrderStatusTable.STATUS);
        Order.Status status = Order.Status.valueOf(statusStr.toUpperCase());

        Order order = new Order();
        order.setId(id);
        order.setUserID(userID);
        order.setCarID(carID);
        order.setDateStart(dateStart);
        order.setDateEnd(dateEnd);
        order.setTotalPrice(price);
        order.setStatus(status);

        return order;
    }

    /**
     * Parses an {@link ResultSet} object and creates {@link OrderInfoDTO} object.
     *
     * @param rs is an {@link ResultSet} object to parse.
     * @return an {@link OrderInfoDTO} object.
     * @throws SQLException
     */
    public static OrderInfoDTO createOrderInfoDTO(ResultSet rs) throws SQLException {

        Order order = createOrder(rs);
        User user = createUser(rs);
        Car car = createCar(rs);

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();

        orderInfoDTO.setOrder(order);
        orderInfoDTO.setUser(user);
        orderInfoDTO.setCar(car);

        return orderInfoDTO;

    }

    private ResultSetParser() {

    }

}
