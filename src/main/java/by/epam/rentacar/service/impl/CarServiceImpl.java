package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {

    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private final CarDAO carDAO = daoFactory.getCarDAO();

    public List<Car> getAllCars() throws ServiceException {

        List<Car> carList = new ArrayList<>();

        try {
            carList = carDAO.getAllCars();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Could not find car list", e);
        }

        return carList;
    }

    public Car getSelectedCar(int carID) throws ServiceException {

        Car car = null;

        try {
            car = carDAO.getCarByID(carID);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Could not find the car", e);
        }

        return car;
    }

}
