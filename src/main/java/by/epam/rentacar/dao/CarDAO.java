package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.entity.Car;

import java.util.Date;
import java.util.List;

public abstract class CarDAO extends AbstractDAO<Car> {

    public abstract List<Car> getAllNotDeleted(int page, int itemsPerPage) throws DAOException;
    public abstract List<Car> getAllNotDeletedByDateRange(Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException;
    public abstract List<Car> getAllNotDeletedByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException;

    public abstract int getTotalNotDeletedCount() throws DAOException;
    public abstract int getTotalCountByDateRange(Date dateStart, Date dateEnd) throws DAOException;
    public abstract int getTotalCountByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd) throws DAOException;

    public abstract double getPriceByCarID(int carID) throws DAOException;
    public abstract void addPhoto(int carID, String filename) throws DAOException;

    public abstract void deletePhoto(int photoID) throws DAOException;

}
