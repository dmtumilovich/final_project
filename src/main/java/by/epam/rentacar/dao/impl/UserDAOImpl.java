package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dto.ChangePasswordDTO;
import by.epam.rentacar.entity.User;
import by.epam.rentacar.util.ResultSetParser;
import by.epam.rentacar.util.constant.DBQueries;
import by.epam.rentacar.util.constant.DBSchema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    public User checkUser(String username, String password) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(DBQueries.FIND_USER_BY_USERNAME);
            statement.setString(1, username);

            //отдельный метод
            resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                return null;
            }
            if(!password.equalsIgnoreCase(resultSet.getString(3))) {
                return null;
            }

            user = ResultSetParser.createUser(resultSet);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection error!", e);
        } catch (SQLException e) {
            throw new DAOException("Error while getting user info by id.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return user;
    }


    //убрать бул или переделать
    public boolean signupUser(String username, String password, String email) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {

            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(DBQueries.ADD_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection error!", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding new user to the database.", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

        return true;
    }

    //тоже убрать бул или переделать
    public boolean updateUser(User user) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(DBQueries.UPDATE_USER_INFO);
            statement.setString(1,  user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getPassport());
            statement.setInt(5, user.getId());
            statement.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection error!", e);
        } catch (SQLException e) {
            throw new DAOException("Error while updating user info.", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

        return true;
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement("UPDATE user_list SET password = ? WHERE id_user = ?");
            statement.setString(1, changePasswordDTO.getNewPassword());
            statement.setInt(2, changePasswordDTO.getUserID());
            statement.executeUpdate();

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean checkPassword(int userID, String password) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        boolean isMatches = false;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement("SELECT * FROM user_list WHERE id_user = ?");
            statement.setInt(1, userID);

            resultSet = statement.executeQuery();
            //????
            if (!resultSet.next()) {
                return false;
            }

            String userPassword = resultSet.getString(DBSchema.UserListTable.PASSWORD);
            isMatches = password.equalsIgnoreCase(userPassword);

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isMatches;
    }

}
