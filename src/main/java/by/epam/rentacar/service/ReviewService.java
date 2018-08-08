package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.AddReviewDTO;
import by.epam.rentacar.domain.entity.Review;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.List;

public interface ReviewService {

    void addReview(AddReviewDTO reviewDTO) throws ServiceException;
    void deleteReview(int reviewID) throws ServiceException;

}
