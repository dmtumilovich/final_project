package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.ReviewDAO;
import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.ReviewDAOImpl;
import by.epam.rentacar.domain.entity.Review;
import by.epam.rentacar.service.ReviewService;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.Date;

public class ReviewServiceImpl implements ReviewService {

    @Override
    public void addReview(Review review) throws ServiceException {

        ReviewDAO reviewDAO = new ReviewDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(reviewDAO);

            Date reviewDate = new Date();
            review.setReviewDate(reviewDate);

            reviewDAO.add(review);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while adding review!", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    @Override
    public void deleteReview(int reviewID) throws ServiceException {

        ReviewDAO reviewDAO = new ReviewDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(reviewDAO);

            reviewDAO.delete(reviewID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while deleting review!", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }
}
