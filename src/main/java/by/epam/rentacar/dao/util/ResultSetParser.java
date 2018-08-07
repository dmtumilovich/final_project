package by.epam.rentacar.dao.util;

import static by.epam.rentacar.dao.util.constant.DBSchema.*;

import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.domain.entity.Review;
import by.epam.rentacar.domain.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ResultSetParser {

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

        //builder???
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

    public static Car createCar(ResultSet rs) throws SQLException {

        int id = rs.getInt(CarListTable.ID_CAR);
        String brand = rs.getString(CarListTable.BRAND);
        String model = rs.getString(CarListTable.MODEL);
        String carClass = rs.getString(CarListTable.CLASS);
        int yearOfIssue = rs.getInt(CarListTable.YEAR_OF_ISSUE);
        int numberOfSeats = rs.getInt(CarListTable.NUMBER_OF_SEATS);
        String color = rs.getString(CarListTable.COLOR);
        double engineVolume = rs.getDouble(CarListTable.ENGINE_VOLUME);
        boolean isAvailable = rs.getBoolean(CarListTable.IS_AVAILABLE);
        double price = rs.getDouble(CarListTable.PRICE);

        //builder???
        Car car = new Car();
        car.setId(id);
        car.setBrand(brand);
        car.setModel(model);
        car.setCarClass(carClass);
        car.setYearOfIssue(yearOfIssue);
        car.setNumberOfSeats(numberOfSeats);
        car.setColor(color);
        car.setEngineVolume(engineVolume);
        car.setAvailable(isAvailable);
        car.setPrice(price);

        return car;
    }

    //?????
    public static Review createReview(ResultSet rs) throws SQLException {
        int reviewID = rs.getInt(CarReviewTable.ID_REVIEW);
        if (reviewID == 0) {
            return null;
        }

        int userID = rs.getInt(CarReviewTable.ID_USER);
        String username = rs.getString(UserListTable.USERNAME);
        String userPhotoUrl = rs.getString(UserListTable.PHOTO);
        String reviewText = rs.getString(CarReviewTable.REVIEW_TEXT);
        Date date = rs.getDate(CarReviewTable.DATE);

        Review review = new Review();
        review.setId(reviewID);
        review.setUsername(username);
        review.setUserPhotoUrl(userPhotoUrl);
        review.setUserID(userID);
        review.setReviewText(reviewText);
        review.setReviewDate(date);

        return review;
    }

    public static Order createOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt(OrderListTable.ID_ORDER);
        int userID = rs.getInt(OrderListTable.ID_USER);
        int carID = rs.getInt(OrderListTable.ID_CAR);
        Date dateStart = rs.getDate(OrderListTable.DATE_START);
        Date dateEnd = rs.getDate(OrderListTable.DATE_END);
        double price = rs.getDouble(OrderListTable.TOTAL_PRICE);
        String statusStr= rs.getString(OrderStatusTable.STATUS);
        Order.OrderStatus status = Order.OrderStatus.valueOf(statusStr.toUpperCase());
        System.out.println("status - " + status);

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

    public static OrderInfoDTO createOrderInfoDTO(ResultSet rs) throws SQLException {

        int userID = rs.getInt(OrderListTable.ID_USER);
        String username = rs.getString(UserListTable.USERNAME);
        String name = rs.getString(UserListTable.NAME);
        String surname = rs.getString(UserListTable.SURNAME);
        String phone = rs.getString(UserListTable.PHONE_NUMBER);

        int carID = rs.getInt(OrderListTable.ID_CAR);
        String brand = rs.getString(CarListTable.BRAND);
        String model = rs.getString(CarListTable.MODEL);
        String carClass = rs.getString(CarListTable.CLASS);
        double price = rs.getDouble(CarListTable.PRICE);

        Order order = createOrder(rs);

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();

        orderInfoDTO.setOrder(order);

        orderInfoDTO.setUserID(userID);
        orderInfoDTO.setUsername(username);
        orderInfoDTO.setName(name);
        orderInfoDTO.setSurname(surname);
        orderInfoDTO.setPhone(phone);

        orderInfoDTO.setCarID(carID);
        orderInfoDTO.setBrand(brand);
        orderInfoDTO.setModel(model);
        orderInfoDTO.setCarClass(carClass);
        orderInfoDTO.setPrice(price);

        return orderInfoDTO;

    }

    private ResultSetParser() {

    }

}
