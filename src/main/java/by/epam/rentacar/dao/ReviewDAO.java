package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.entity.Review;

import java.util.List;

/**
 * Defines abstract methods to work with reviews in the database table.
 */
public abstract class ReviewDAO extends AbstractDAO<Review> {

    /**
     * Gets list of all reviews according to the car id.
     *
     * @param carID the car id.
     * @return the list of all reviews.
     * @throws DAOException
     */
    public abstract List<Review> getAllByCarID(int carID) throws DAOException;

}
