package by.epam.rentacar.dao;

import by.epam.rentacar.domain.dto.AddReviewDTO;

public interface ReviewDAO {

    void addReview(AddReviewDTO reviewDTO);
    void deleteReview(int reviewID);

}
