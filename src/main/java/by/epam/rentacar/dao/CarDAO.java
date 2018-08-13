package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.CarSearchDTO;
import by.epam.rentacar.domain.dto.FindCarsDTO;
import by.epam.rentacar.domain.entity.Car;

import java.util.Date;
import java.util.List;

public abstract class CarDAO extends AbstractDAO {

    public abstract List<Car> getAllCars() throws DAOException;
    public abstract List<Car> getAllNotDeleted(int page, int itemsPerPage) throws DAOException;

    public abstract List<Car> getAllByDateRange(Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException;
    public abstract List<Car> getAllByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException;

    public abstract Car getCarByID(int carID) throws DAOException;
    public abstract void deleteCar(int carID) throws DAOException;
    public abstract List<Car> getCarsByFilter(CarSearchDTO carSearchDTO) throws DAOException;
    public abstract double getPriceByCarID(int carID) throws DAOException;
    public abstract void addPhoto(int carID, String filename) throws DAOException;
    public abstract void deletePhoto(int photoID) throws DAOException;

    public abstract int getTotalCount() throws DAOException;
    public abstract int getTotalCountByDateRange(Date dateStart, Date dateEnd) throws DAOException;
    public abstract int getTotalCountByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd) throws DAOException;

}
