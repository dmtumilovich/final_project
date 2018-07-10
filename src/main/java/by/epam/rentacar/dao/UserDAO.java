package by.epam.rentacar.dao;

import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.entity.User;
import by.epam.rentacar.util.ResultSetParser;
import by.epam.rentacar.util.constant.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User checkUser(String username, String password) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        User user = null;

        try {
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_USER_BY_USERNAME);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                return null;
            }
            if(!password.equalsIgnoreCase(resultSet.getString(3))) {
                return null;
            }

            user = ResultSetParser.createUser(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            //DAOException
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
            //DAOException
        }

        return user;
    }


    public boolean signupUser(String username, String password, String email) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        
        try {

            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement statement = connection.prepareStatement(DBQueries.ADD_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //DAOException
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
            //DAOException
        }

        return true;
    }

    public boolean updateUser(User user) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement statement = connection.prepareStatement(DBQueries.UPDATE_USER_INFO    );
            statement.setString(1,  user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getPassport());
            statement.setInt(5, user.getId());
            statement.executeUpdate();

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
