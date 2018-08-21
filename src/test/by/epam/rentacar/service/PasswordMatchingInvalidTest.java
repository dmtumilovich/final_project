package by.epam.rentacar.service;

import by.epam.rentacar.service.validation.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PasswordMatchingInvalidTest {

    private String password;
    private String confirmPassword;

    public PasswordMatchingInvalidTest(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> passwordsValues() {
        return Arrays.asList(new Object[][]{
                {"qwerty", "qwerty1"},
                {"password", "password "},
                {"admin", "aDmin"}
        });
    }

    @Test
    public void testPasswords() {
        Assert.assertFalse(Validator.isPasswordsEqual(password, confirmPassword));
    }

}
