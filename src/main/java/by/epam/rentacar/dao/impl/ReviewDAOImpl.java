package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.ReviewDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;
import by.epam.rentacar.domain.dto.AddReviewDTO;
import by.epam.rentacar.domain.entity.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl extends ReviewDAO {

    @Override
    public List<Review> getCarReviews(int carID) throws DAOException {

        List<Review> reviewList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT id_review, car_review.id_user, review, time, user_list.username, user_list.photo\n" +
                                                        "FROM car_review\n" +
                                                        "LEFT JOIN user_list\n" +
                                                        "ON car_review.id_user = user_list.id_user\n" +
                                                        "WHERE car_review.id_car = ?\n" +
                                                        "ORDER BY time DESC");
            statement.setInt(1, carID);

            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Review review = ResultSetParser.createReview(resultSet);
                reviewList.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while getting reviews by car id", e);
        }

        return reviewList;

    }

    @Override
    public void addReview(AddReviewDTO reviewDTO) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.INSERT_REVIEW);
            statement.setInt(1, reviewDTO.getCarID());
            statement.setInt(2, reviewDTO.getUserID());
            statement.setString(3, reviewDTO.getReviewText());
            Timestamp timestamp = new Timestamp(reviewDTO.getReviewDate().getTime());
            statement.setTimestamp(4, timestamp);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //переделать
    @Override
    public void deleteReview(int reviewID) {

        PreparedStatement statement;

        try {

            statement = connection.prepareStatement("DELETE FROM rent_a_car.car_review WHERE id_review = ?");
            statement.setInt(1, reviewID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
