package by.epam.rentacar.service;

import by.epam.rentacar.domain.entity.Review;
import by.epam.rentacar.service.exception.ServiceException;

public interface ReviewService {

    void addReview(Review review) throws ServiceException;
    void deleteReview(int reviewID) throws ServiceException;

}
