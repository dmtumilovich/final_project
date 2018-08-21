package by.epam.rentacar.service;

import by.epam.rentacar.service.validation.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PasswordMatchingValidTest {

    private String password;
    private String confirmPassword;

    public PasswordMatchingValidTest(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> passwordsValues() {
        return Arrays.asList(new Object[][]{
                {"qwerty", "qwerty"},
                {"password", "password"},
                {"admin", "admin"}
        });
    }

    @Test
    public void testPasswords() {
        Assert.assertTrue(Validator.isPasswordsEqual(password, confirmPassword));
    }

}
