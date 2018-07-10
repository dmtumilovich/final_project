package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.entity.Car;
import by.epam.rentacar.entity.User;

import java.util.List;

public interface UserDAO {

    User checkUser(String username, String password) throws DAOException;
    boolean signupUser(String username, String password, String email) throws DAOException;
    boolean updateUser(User user) throws DAOException;

}
