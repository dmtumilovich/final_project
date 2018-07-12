package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dto.CarSearchDTO;
import by.epam.rentacar.entity.Car;

import java.util.List;

public interface CarDAO {

    List<Car> getAllCars() throws DAOException;
    Car getCarByID(int carID) throws DAOException;
    List<Car> getCarsByFilter(CarSearchDTO carSearchDTO) throws DAOException;

}
