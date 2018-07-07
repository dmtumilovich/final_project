package by.epam.rentacar.dao;

import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.entity.User;

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

            PreparedStatement statement = connection.prepareStatement("SELECT id_user, username, password FROM user_list WHERE username = ?");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                return null;
            }
            if(!password.equalsIgnoreCase(resultSet.getString(3))) {
                return null;
            }

            user = new User();
            user.setId(resultSet.getInt(1));
            user.setUsername(resultSet.getString(2));


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

            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_list (username, password, email, id_role) VALUES (?, ?, ?, '2')");
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



}
