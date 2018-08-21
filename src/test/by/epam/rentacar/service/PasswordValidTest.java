package by.epam.rentacar.service;

import by.epam.rentacar.service.validation.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class PasswordValidTest {

    private String password;

    public PasswordValidTest(String password) {
        this.password = password;
    }

    @Parameterized.Parameters
    public static List<String> passwordsForTest() {
        return Arrays.asList(
                "qwerty",
                "this1ismy23password",
                "new_password",
                "_valid_"
        );
    }

    @Test
    public void testPasswords() {
        Assert.assertTrue(Validator.isPasswordValid(password));
    }

}
