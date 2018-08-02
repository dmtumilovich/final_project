package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.ReviewDAO;
import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.ReviewDAOImpl;
import by.epam.rentacar.domain.dto.AddReviewDTO;
import by.epam.rentacar.service.ReviewService;

import java.util.Date;

public class ReviewServiceImpl implements ReviewService {

    @Override
    public void addReview(AddReviewDTO reviewDTO) {

        ReviewDAO reviewDAO = new ReviewDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(reviewDAO);

            Date reviewDate = new Date();
            reviewDTO.setReviewDate(reviewDate);

            reviewDAO.addReview(reviewDTO);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }

    }

    @Override
    public void deleteReview(int reviewID) {

        ReviewDAO reviewDAO = new ReviewDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(reviewDAO);

            reviewDAO.deleteReview(reviewID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }

    }
}
