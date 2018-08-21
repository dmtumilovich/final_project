package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.entity.Car;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Defines abstract methods to work with car data in the database.
 */
public abstract class CarDAO extends AbstractDAO<Car> {

    /**
     * Gets list of all cars which have is_deleted = 0 status according to the page and items per this page.
     *
     * @param page is the page.
     * @param itemsPerPage is the items per this page.
     * @return the list of cars.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract List<Car> getAllNotDeleted(int page, int itemsPerPage) throws DAOException;

    /**
     * Gets list of all cars which have is_deleted = 0 status according to the selected date range, page and items per this page.
     *
     * @param page is the page.
     * @param itemsPerPage is the items per this page.
     * @return the list of all available for this date range cars.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract List<Car> getAllNotDeletedByDateRange(Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException;

    /**
     * Gets list of all cars which have is_deleted = 0 status according to the car class, selected date range, page and items per this page.
     *
     * @param page is the page.
     * @param itemsPerPage is the items per this page.
     * @return the list of all available for this date range cars which have appropriate car class.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract List<Car> getAllNotDeletedByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException;

    /**
     * Gets total count of all not deleted cars.
     *
     * @return the total count of cars.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract int getTotalNotDeletedCount() throws DAOException;

    /**
     * Gets total count of all not deleted cars according to the selected date range.
     *
     * @return the total count of cars.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract int getTotalCountByDateRange(Date dateStart, Date dateEnd) throws DAOException;

    /**
     * Gets total count of all not deleted cars according to the selected date range and car class.
     *
     * @return the total count of cars.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract int getTotalCountByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd) throws DAOException;

    /**
     * Gets car price by it's id.
     *
     * @param carID is the car id.
     * @return car price.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract double getPriceByCarID(int carID) throws DAOException;

    /**
     * Checks if car busy or not in the selected date range.
     *
     * @param carID is the id of the car.
     * @param dateStart is the start date.
     * @param dateEnd is the end date.
     * @return true if car is available in the selected date range or false if not.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract boolean isCarAvailable(int carID, Date dateStart, Date dateEnd) throws DAOException;

    /**
     * Adds car photo to the database table.
     *
     * @param carID is the car id.
     * @param filename is the photo's url.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void addPhoto(int carID, String filename) throws DAOException;

    /**
     * Deletes car photo from the database table.
     *
     * @param photoID is the car's photo id.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void deletePhoto(int photoID) throws DAOException;

}
