package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.UserDAOImpl;
import by.epam.rentacar.entity.User;
import by.epam.rentacar.service.UserService;
import by.epam.rentacar.service.exception.ServiceException;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class UserServiceImpl implements UserService {

    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private final UserDAO userDAO = daoFactory.getUserDAO();

    public User login(String username, String password) throws ServiceException {

        User user = null;
        String hashedPassword = hashPassword(password);

        try {
            user = userDAO.checkUser(username, hashedPassword);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Could not login user", e);
        }

        return user;
    }


    public User signup(String username, String password, String email) throws ServiceException {

        User user = null;
        String hashedPassword = hashPassword(password);

        try {
            userDAO.signupUser(username, hashedPassword, email);
            user = userDAO.checkUser(username, hashedPassword);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Could not sign up user", e);
        }

        return user;
    }

    //переделать
    public boolean editProfile(User user) throws ServiceException {

        try {
            userDAO.updateUser(user);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Could not update user info", e);
        }

        return true;
    }

    private String hashPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
