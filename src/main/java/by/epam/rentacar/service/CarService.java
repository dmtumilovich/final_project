package by.epam.rentacar.service;

import by.epam.rentacar.entity.Car;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.List;

public interface CarService {

    List<Car> getAllCars() throws ServiceException;
    Car getSelectedCar(int carID) throws ServiceException;

}
