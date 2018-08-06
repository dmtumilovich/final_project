package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.AddReviewDTO;
import by.epam.rentacar.service.exception.ServiceException;

public interface ReviewService {

    void addReview(AddReviewDTO reviewDTO) throws ServiceException;
    void deleteReview(int reviewID) throws ServiceException;

}
