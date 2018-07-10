package by.epam.rentacar.service;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.entity.Car;

import java.util.Collections;
import java.util.List;

public class CarService {
    private static final CarDAO carDAO = new CarDAO();

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getSelectedCar(int carID) {
        return carDAO.getCarByID(carID);
    }

}
