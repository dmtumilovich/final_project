package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.CarDTO;
import by.epam.rentacar.domain.dto.FindCarsDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.List;

public interface CarService {

    List<Car> getAllCars(int page, int itemsPerPage) throws ServiceException;
    List<Car> getAllNotDeletedCars(int page, int itemsPerPage) throws ServiceException;
    int getCarsPagesCount(int itemsPerPage) throws ServiceException;

    List<Car> getCarsByDateRangeAndClass(FindCarsDTO findCarsDTO, int page, int itemsPerPage) throws ServiceException;
    int getCarsPagesCount(FindCarsDTO findCarsDTO, int itemsPerPage) throws ServiceException;

    Car getCar(int carID) throws ServiceException;

    void addCar(CarDTO carDTO) throws ServiceException;
    void editCar(CarDTO carDTO) throws ServiceException;
    void deleteCar(int carID) throws ServiceException;

    void addPhoto(int carID, String filename) throws ServiceException;
    void deletePhoto(int photoID) throws ServiceException;

}
