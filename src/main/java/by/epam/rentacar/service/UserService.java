package by.epam.rentacar.service;

import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.entity.User;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class UserService {

    private static final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {

        String hashedPassword = hashPassword(password);
        return userDAO.checkUser(username, hashedPassword);

    }


    public User signup(String username, String password, String email) {
        String hashedPassword = hashPassword(password);
        if(!userDAO.signupUser(username, hashedPassword, email)) {
            return null;
        }
        return userDAO.checkUser(username, hashedPassword);
    }

    //переделать
    public boolean editProfile(User user) {
        return userDAO.updateUser(user);
    }

    private String hashPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
