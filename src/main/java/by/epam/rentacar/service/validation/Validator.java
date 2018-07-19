package by.epam.rentacar.service.validation;

import by.epam.rentacar.dto.SigninDTO;
import by.epam.rentacar.dto.SignupDTO;
import com.sun.org.apache.regexp.internal.RE;

public class Validator {

    private static final String REGEXP_USERNAME = "[A-Za-z0-9-_.]{4,12}";
    private static final String REGEXP_EMAIL = "[A-Za-z_]+[A-Za-z._-]*@[A-Za-z0-9]+.[a-z]{2,}";
    private static final String REGEXP_PASSWORD = "[A-Za-z0-9_]{6,20}";

    public static boolean isEmptyFieldInForm(SigninDTO signinDTO) {
        return signinDTO.getUsername().isEmpty() || signinDTO.getPassword().isEmpty();
    }

    public static boolean isEmptyFieldInForm(SignupDTO signupDTO) {
        return signupDTO.getUsername().isEmpty() || signupDTO.getEmail().isEmpty() ||
                signupDTO.getPassword().isEmpty() || signupDTO.getConfirmPassword().isEmpty();
    }

    public static boolean isUsernameValid(String username) {
        return username.matches(REGEXP_USERNAME);
    }

    public static boolean isEmailValid(String email) {
        return email.matches(REGEXP_EMAIL);
    }

    public static boolean isPasswordValid(String password) {
        return password.matches(REGEXP_PASSWORD);
    }

    public static boolean isPasswordEquals(SignupDTO signupDTO) {
        return signupDTO.getPassword().equals(signupDTO.getConfirmPassword());
    }

}
