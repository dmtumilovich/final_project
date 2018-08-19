package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.entity.User;

import java.sql.SQLException;

/**
 * Defines abstract methods to work with user data in the database.
 */
public abstract class UserDAO extends AbstractDAO<User> {

    /**
     * Gets user from database depending on username and password.
     *
     * @param username is the user's username.
     * @param password is the user's password.
     * @return the {@link User} object if it was found or null if not.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract User findUserByUsernameAndPassword(String username, String password) throws DAOException;

    /**
     * Gets user from database depending on username.
     *
     * @param username is the user's username.
     * @return the {@link User} object if it was found or null if not.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract User findUserByUsername(String username) throws DAOException;

    public abstract int findUserIdByUsername(String username) throws DAOException;
    public abstract String findPasswordByUsername(String username) throws DAOException; //maybe I'll delete it later

    /**
     * Checks if username exists in the database table.
     *
     * @param username is the user's username.
     * @return true if this username exists or false if not.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract boolean isUsernameAlreadyExists(String username) throws DAOException;

    /**
     * Checks if email exists in the database table.
     *
     * @param email is the user's email.
     * @return true if this email exists or false if not.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract boolean isEmailAlreadyExists(String email) throws DAOException;

    /**
     * Adds a new user to the database table.
     *
     * @param username is the user's username.
     * @param email is the user's email.
     * @param password is the user's password.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void add(String username, String email, String password) throws DAOException;

    /**
     * Changes user password in the database table according to it's id and new password.
     *
     * @param id the user's id.
     * @param newPassword the user's new password.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void changePassword(int id, String newPassword) throws DAOException;

    /**
     * Sets new photo url to the user in the database table.
     *
     * @param userID is the user's id.
     * @param filename is the photo url.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void setPhoto(int userID, String filename) throws DAOException;

}
