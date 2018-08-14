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

    private static final int DELETED = 1;

    @Override
    public void add(Review review) throws DAOException {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.INSERT_REVIEW);
            statement.setInt(1, review.getCarID());
            statement.setInt(2, review.getUserID());
            statement.setString(3, review.getReviewText());
            Timestamp timestamp = new Timestamp(review.getReviewDate().getTime());
            statement.setTimestamp(4, timestamp);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while inserting review to the db!", e);
        }

    }

    @Override
    public void update(Review obj) throws DAOException {
        throw new UnsupportedOperationException("Invalid operation for reviewDAO");
    }

    @Override
    public void delete(int reviewID) {

        PreparedStatement statement;

        try {

            statement = connection.prepareStatement(DBQueries.DELETE_REVIEW);
            statement.setInt(1, DELETED);
            statement.setInt(2, reviewID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Review getByID(int id) throws DAOException {
        throw new UnsupportedOperationException("Invalid operation for reviewDAO");
    }

    @Override
    public List<Review> getAll() throws DAOException {
        throw new UnsupportedOperationException("Invalid operation for reviewDAO");
    }

    @Override
    public List<Review> getAll(int page, int itemsPerPage) throws DAOException {
        throw new UnsupportedOperationException("Invalid operation for reviewDAO");
    }

    @Override
    public int getTotalCount() throws DAOException {
        throw new UnsupportedOperationException("Invalid operation for reviewDAO");
    }

    @Override
    public List<Review> getAllByCarID(int carID) throws DAOException {

        List<Review> reviewList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_ALL_REVIEWS_BY_CAR_ID);
            statement.setInt(1, carID);
            statement.setInt(2, DELETED);

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
}
