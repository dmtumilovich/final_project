package by.epam.rentacar.service.validation;

import by.epam.rentacar.domain.dto.CarDTO;
import by.epam.rentacar.domain.dto.SigninDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.User;
import org.apache.commons.validator.GenericValidator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Validator {

    private static final String REGEX_USERNAME = "[A-Za-z0-9-_.]{4,12}";
    private static final String REGEX_PASSWORD = "[A-Za-z0-9_]{4,20}";
    private static final String REGEX_NAME = "[A-Za-zА-Яа-я'-]{2,25}";
    private static final String REGEX_PHONE = "[0-9\\+]{7,15}";
    private static final String REGEX_PASSPORT = "[a-zA-Z0-9]{3,20}";

    private static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);

    private static final int MIN_CAR_ID = 1;
    private static final int MAX_CAR_ID = Integer.MAX_VALUE;
    private static final int MIN_NUMBER_OF_SEATS = 2;
    private static final int MAX_NUMBER_OF_SEATS = 10;
    private static final int MIN_YEAR = 1970;
    private static final int MAX_YEAR = 2018;
    private static final double MIN_ENGINE_VOLUME = 0.5d;
    private static final double MAX_ENGINE_VOLUME = 10.0d;
    private static final double MIN_PRICE = 0d;
    private static final double MAX_PRICE = Double.MAX_VALUE;

    public static boolean isSigninDataValid(SigninDTO signinDTO) {

        String username = signinDTO.getUsername();
        String password = signinDTO.getPassword();

        return isUsernameValid(username) && isPasswordValid(password);
    }

    public static boolean isSignupDataValid(SignupDTO signupDTO) {

        String username = signupDTO.getUsername();
        String email = signupDTO.getEmail();
        String password = signupDTO.getPassword();
        String confirmPassword = signupDTO.getConfirmPassword();

        return isUsernameValid(username)
                && isEmailValid(email)
                && isPasswordValid(password)
                && isPasswordValid(confirmPassword);

    }

    public static boolean isPasswordsEqual(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean isEditProfileDataValid(User user) {

        String name = user.getName();
        String surname = user.getSurname();
        String phone = user.getPhone();
        String passport = user.getPassport();

        return !GenericValidator.isBlankOrNull(name)
                && !GenericValidator.isBlankOrNull(surname)
                && !GenericValidator.isBlankOrNull(phone)
                && !GenericValidator.isBlankOrNull(passport)
                && name.matches(REGEX_NAME)
                && surname.matches(REGEX_NAME)
                && phone.matches(REGEX_PHONE)
                && passport.matches(REGEX_PASSPORT);

    }

    public static boolean isDateRangeValid(Date dateStart, Date dateEnd) {

        Date currentDate = new Date();
        return !dateStart.before(currentDate)
                && !dateEnd.before(currentDate)
                && dateEnd.after(dateStart)
                && (dateEnd.getTime() - dateStart.getTime()) >= DAY_IN_MILLIS;

    }

    public static boolean isCarDataValid(CarDTO car) {

        String brand = car.getBrand();
        String model = car.getModel();
        String carClass = car.getCarClass();
        String color = car.getColor();
        String yearOfIssue = car.getYearOfIssue();
        String numberOfSeats = car.getNumberOfSeats();
        String engineVolume = car.getEngineVolume();
        String price = car.getPrice();

        return  !GenericValidator.isBlankOrNull(brand) && !GenericValidator.isBlankOrNull(model)
                && !GenericValidator.isBlankOrNull(carClass) && !GenericValidator.isBlankOrNull(color)
                && !GenericValidator.isBlankOrNull(yearOfIssue) && !GenericValidator.isBlankOrNull(numberOfSeats)
                && !GenericValidator.isBlankOrNull(engineVolume) && !GenericValidator.isBlankOrNull(price)
                && GenericValidator.isInt(numberOfSeats) && GenericValidator.isInRange(Integer.parseInt(numberOfSeats), MIN_NUMBER_OF_SEATS, MAX_NUMBER_OF_SEATS)
                && GenericValidator.isInt(yearOfIssue) && GenericValidator.isInRange(Integer.parseInt(yearOfIssue), MIN_YEAR, MAX_YEAR)
                && GenericValidator.isDouble(engineVolume) && GenericValidator.isInRange(Double.parseDouble(engineVolume), MIN_ENGINE_VOLUME, MAX_ENGINE_VOLUME)
                && GenericValidator.isDouble(price) && GenericValidator.isInRange(Double.parseDouble(price), MIN_PRICE, MAX_PRICE);

    }

    public static boolean isUsernameValid(String username) {
        return !GenericValidator.isBlankOrNull(username) && username.matches(REGEX_USERNAME);
    }

    public static boolean isEmailValid(String email) {
        return !GenericValidator.isBlankOrNull(email) && GenericValidator.isEmail(email);
    }

    public static boolean isPasswordValid(String password) {
        return !GenericValidator.isBlankOrNull(password) && password.matches(REGEX_PASSWORD);
    }

    public static boolean isNotEmpty(String value) {
        return !GenericValidator.isBlankOrNull(value);
    }

}
