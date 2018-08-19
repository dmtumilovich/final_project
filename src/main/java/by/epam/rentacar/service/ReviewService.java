package by.epam.rentacar.service;

import by.epam.rentacar.domain.entity.Review;
import by.epam.rentacar.service.exception.ServiceException;

/**
 * The interface defines methods to work with car reviews.
 */
public interface ReviewService {

    /**
     * Adds a new review.
     *
     * @param review is an {@link Review} object, containing information about new review.
     * @throws ServiceException
     */
    void addReview(Review review) throws ServiceException;

    /**
     * Deletes the review with appropriate {@code reviewID}.
     *
     * @param reviewID is the id of the review to delete.
     * @throws ServiceException
     */
    void deleteReview(int reviewID) throws ServiceException;

}
