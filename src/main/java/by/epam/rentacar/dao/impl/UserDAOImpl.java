package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.EditProfileDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;
import by.epam.rentacar.dao.util.constant.DBSchema;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends UserDAO {

    @Override
    public void add(User user) throws DAOException {
        throw new UnsupportedOperationException("Method add(User user) is not available");
    }

    @Override
    public void add(String username, String email, String password) throws DAOException {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.ADD_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while adding new user to the database.", e);
        }

    }

    @Override
    public void update(User user) throws DAOException {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.UPDATE_USER_INFO);
            statement.setString(1,  user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getPassport());
            statement.setInt(5, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while updating user info.", e);
        }

    }

    @Override
    public void delete(int id) throws DAOException {
        throw new UnsupportedOperationException("Method delete(int id) is unavailable!");
    }

    @Override
    public User getByID(int userID) throws DAOException {

        User user = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.FIND_USER_BY_ID);
            statement.setInt(1, userID);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = ResultSetParser.createUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while getting user by id", e);
        }

        return user;

    }

    @Override
    public List<User> getAll() throws DAOException {
        return null;
    }

    @Override
    public List<User> getAll(int page, int itemsPerPage) throws DAOException {
        return null;
    }

    @Override
    public int getTotalCount() throws DAOException {
        return 0;
    }

    ///////////////////////////////////////////////////////////////

    public User findUserByUsernameAndPassword(String username, String password) throws DAOException {

        User user = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(DBQueries.FIND_USER_BY_USERNAME);
            statement.setString(1, username);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = ResultSetParser.createUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error while getting user info by id.", e);
        }

        return user;
    }

    @Override
    public User findUserByUsername(String username) throws DAOException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        User user = null;

        try {

            statement = connection.prepareStatement(DBQueries.FIND_USER_BY_USERNAME);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = ResultSetParser.createUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public int findUserIdByUsername(String username) throws DAOException {

        int userID = -1;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(DBQueries.FIND_USER_ID_BY_USERNAME);
            statement.setString(1, username);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userID = resultSet.getInt(DBSchema.UserListTable.ID_USER);
            }

        } catch (SQLException e) {
            throw new DAOException("Error while finding user id by username", e);
        }

        return userID;

    }

    @Override
    public String findPasswordByUsername(String username) throws DAOException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String password = null;

        try {

            statement = connection.prepareStatement(DBQueries.FIND_PASSWORD_BY_USERNAME);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                password = resultSet.getString(DBSchema.UserListTable.PASSWORD);
            }

        } catch (SQLException e) {
            throw new DAOException("Error while finding password by username", e);
        }

        return password;
    }

    @Override
    public boolean isUsernameAlreadyExists(String username) throws DAOException {
        return false;
    }

    @Override
    public boolean isEmailAlreadyExists(String email) throws DAOException {
        return false;
    }

    @Override
    public void changePassword(int id, String newPassword) throws DAOException {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.UPDATE_PASSWORD);
            statement.setString(1, newPassword);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while updating password!", e);
        }

    }

    @Override
    public void setPhoto(int userID, String filename) throws DAOException {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.SET_USER_PHOTO);
            statement.setString(1, filename);
            statement.setInt(2, userID);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("error while updating user photo!", e);
        }

    }

}