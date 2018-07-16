package by.epam.rentacar.service;

import by.epam.rentacar.dto.AddReviewDTO;

public interface ReviewService {

    void addReview(AddReviewDTO reviewDTO);
    void deleteReview(int reviewID);

}
