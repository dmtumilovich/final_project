package by.epam.rentacar.dao.util;

import static by.epam.rentacar.dao.util.constant.DBSchema.*;

import by.epam.rentacar.domain.entity.Car;
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
        String reviewText = rs.getString(CarReviewTable.REVIEW_TEXT);
        Date date = rs.getDate(CarReviewTable.DATE);

        Review review = new Review();
        review.setId(reviewID);
        review.setUsername(username);
        review.setUserID(userID);
        review.setReviewText(reviewText);
        review.setReviewDate(date);

        return review;
    }

    private ResultSetParser() {

    }

}
