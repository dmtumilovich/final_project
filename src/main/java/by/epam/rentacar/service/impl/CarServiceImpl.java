package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.domain.dto.CarSearchDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.exception.ServiceException;
import com.sun.corba.se.spi.transport.TransportDefault;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {

    public List<Car> getAllCars() throws ServiceException {

        List<Car> carList = new ArrayList<>();

        CarDAO carDAO = new CarDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carList = carDAO.getAllCars();

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Could not find car list", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return carList;
    }

    public Car getSelectedCar(int carID) throws ServiceException {

        Car car = null;

        CarDAO carDAO = new CarDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            car = carDAO.getCarByID(carID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Could not find the car", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return car;
    }

    @Override
    public List<Car> getCarsByFilter(CarSearchDTO carSearchDTO) throws ServiceException {

        List<Car> carList = new ArrayList<>();

        CarDAO carDAO = new CarDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carList = carDAO.getCarsByFilter(carSearchDTO);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Could not find cars by filter");
        } finally {
            transactionHelper.endTransaction();
        }

        return carList;
    }

}
