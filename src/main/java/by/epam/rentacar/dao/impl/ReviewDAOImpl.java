package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.ReviewDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.domain.dto.AddReviewDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ReviewDAOImpl implements ReviewDAO {

    private ConnectionPool connectionPool;

    public ReviewDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addReview(AddReviewDTO reviewDTO) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

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

    @Override
    public void deleteReview(int reviewID) {

        Connection connection;
        PreparedStatement statement;

        try {

            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement("DELETE FROM rent_a_car.car_review WHERE id_review = ?");
            statement.setInt(1, reviewID);

            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
