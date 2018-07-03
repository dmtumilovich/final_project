package by.epam.rentacar.dao;

import by.epam.rentacar.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    public User checkUser(String username, String password) {
        Connection connection = DBHelper.getConnection();
        User user = null;

        try {
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
        }

        return user;
    }


    public boolean signupUser(String username, String password, String email) {
        Connection connection = DBHelper.getConnection();
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_list (username, password, email, id_role) VALUES (?, ?, ?, '2')");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }



}
