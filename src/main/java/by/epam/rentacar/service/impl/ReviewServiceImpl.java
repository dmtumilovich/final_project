package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.ReviewDAO;
import by.epam.rentacar.domain.dto.AddReviewDTO;
import by.epam.rentacar.service.ReviewService;

import java.util.Date;

public class ReviewServiceImpl implements ReviewService {

    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private final ReviewDAO reviewDAO = daoFactory.getReviewDAO();

    @Override
    public void addReview(AddReviewDTO reviewDTO) {
        Date reviewDate = new Date();
        reviewDTO.setReviewDate(reviewDate);
        reviewDAO.addReview(reviewDTO);
    }

    @Override
    public void deleteReview(int reviewID) {
        reviewDAO.deleteReview(reviewID);
    }
}
