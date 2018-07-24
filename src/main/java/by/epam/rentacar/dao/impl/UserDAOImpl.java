package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.ChangePasswordDTO;
import by.epam.rentacar.domain.dto.SignupDTO;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;
import by.epam.rentacar.dao.util.constant.DBSchema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private ConnectionPool connectionPool;

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public User findUser(String username, String password) throws DAOException {

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

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


    @Override
    public User findUserByUsername(String username) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        User user = null;

        try {

            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(DBQueries.FIND_USER_BY_USERNAME);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = ResultSetParser.createUser(resultSet);
            }

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public String findPasswordByUsername(String username) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String password = null;

        try {

            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement("SELECT password FROM user_list WHERE username = ?");
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                password = resultSet.getString(DBSchema.UserListTable.PASSWORD);
            }

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return password;
    }


    @Override
    public String findEmail(String email) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement("SELECT * FROM user_list WHERE email = ?");
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                email = resultSet.getString(DBSchema.UserListTable.EMAIL);
            }

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return email;
    }

    @Override
    public void registerUser(SignupDTO signupDTO) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(DBQueries.ADD_USER);
            statement.setString(1, signupDTO.getUsername());
            statement.setString(2, signupDTO.getPassword());
            statement.setString(3, signupDTO.getEmail());

            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection error!", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding new user to the database.", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }

    //убрать бул или переделать
    public boolean signupUser(String username, String password, String email) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {

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

        Connection connection = null;
        PreparedStatement statement = null;

        try {

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

        Connection connection = null;
        PreparedStatement statement = null;

        try {

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

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        boolean isMatches = false;

        try {

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
