package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.AddReviewDTO;
import by.epam.rentacar.domain.entity.Review;

import java.util.List;

public abstract class ReviewDAO extends AbstractDAO<Review> {

    public abstract List<Review> getAllByCarID(int carID) throws DAOException;

}
