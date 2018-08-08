package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.AddReviewDTO;
import by.epam.rentacar.domain.entity.Review;

import java.util.List;

public abstract class ReviewDAO extends AbstractDAO {

    public abstract List<Review> getCarReviews(int carID) throws DAOException;
    public abstract void addReview(AddReviewDTO reviewDTO);
    public abstract void deleteReview(int reviewID);

}
