package by.epam.rentacar.dao;

import by.epam.rentacar.domain.dto.AddReviewDTO;

public abstract class ReviewDAO extends AbstractDAO {

    public abstract void addReview(AddReviewDTO reviewDTO);
    public abstract void deleteReview(int reviewID);

}
