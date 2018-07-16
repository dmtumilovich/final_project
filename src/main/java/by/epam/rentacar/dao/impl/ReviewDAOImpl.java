package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.ReviewDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dto.AddReviewDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ReviewDAOImpl implements ReviewDAO {
    @Override
    public void addReview(AddReviewDTO reviewDTO) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement("INSERT INTO car_review (id_car, id_user, review, time) VALUES (?, ?, ?, ?)");
            statement.setInt(1, reviewDTO.getCarID());
            statement.setInt(2, reviewDTO.getUserID());
            statement.setString(3, reviewDTO.getReviewText());
            Timestamp timestamp = new Timestamp(reviewDTO.getReviewDate().getTime());
            statement.setTimestamp(4, timestamp);

            statement.executeUpdate();

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }
}
