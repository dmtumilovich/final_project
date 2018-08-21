package by.epam.rentacar.service;

import by.epam.rentacar.service.validation.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class PasswordInvalidTest {

    private String password;

    public PasswordInvalidTest(String password) {
        this.password = password;
    }

    @Parameterized.Parameters
    public static List<String> passwordsForTest() {
        return Arrays.asList(
                "pass word",
                "$password",
                "pas",
                "thisisverylongpasswordmorethan20symbols"
        );
    }

    @Test
    public void testPasswords() {
        Assert.assertFalse(Validator.isPasswordValid(password));
    }

}
