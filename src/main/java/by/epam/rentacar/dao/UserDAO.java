package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.entity.User;

public abstract class UserDAO extends AbstractDAO<User> {

    public abstract User findUserByUsernameAndPassword(String username, String password) throws DAOException;
    public abstract User findUserByUsername(String username) throws DAOException;

    public abstract int findUserIdByUsername(String username) throws DAOException;
    public abstract String findPasswordByUsername(String username) throws DAOException; //maybe I'll delete it later

    public abstract boolean isUsernameAlreadyExists(String username) throws DAOException;
    public abstract boolean isEmailAlreadyExists(String email) throws DAOException;

    public abstract void add(String username, String email, String password) throws DAOException;

    public abstract void changePassword(int id, String newPassword) throws DAOException;

    public abstract void setPhoto(int userID, String filename) throws DAOException;

}
