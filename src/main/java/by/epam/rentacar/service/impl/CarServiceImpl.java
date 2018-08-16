package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.ReviewDAO;
import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.dao.impl.ReviewDAOImpl;
import by.epam.rentacar.domain.dto.FindCarsDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.CarService;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.service.util.DateParser;
import by.epam.rentacar.service.util.PageCounter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CarServiceImpl implements CarService {

    private static final DAOFactory daoFactory = DAOFactory.getInstance();

    private static final CarDAO carDAO = daoFactory.getCarDAO();
    private static final ReviewDAO reviewDAO = daoFactory.getReviewDAO();

    public List<Car> getAllCars(int page, int itemsPerPage) throws ServiceException {

        List<Car> carList;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carList = carDAO.getAll(page, itemsPerPage);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Could not find car list", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return carList;
    }

    @Override
    public List<Car> getAllNotDeletedCars(int page, int itemsPerPage) throws ServiceException {

        List<Car> carList;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carList = carDAO.getAllNotDeleted(page, itemsPerPage);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Could not find car list", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return carList;

    }


    @Override
    public int getCarsPagesCount(int itemsPerPage) throws ServiceException {

        int pagesCount;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            int carsCount = carDAO.getTotalNotDeletedCount();
            pagesCount = PageCounter.countPages(carsCount, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while counting cars pages!", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return pagesCount;

    }

    @Override
    public List<Car> getCarsByDateRangeAndClass(FindCarsDTO findCarsDTO, int page, int itemsPerPage) throws ServiceException {

        String carClass = findCarsDTO.getCarClass();

        Date dateStart = DateParser.parse(findCarsDTO.getDateStart());
        Date dateEnd = DateParser.parse(findCarsDTO.getDateEnd());

        List<Car> cars;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            cars = (carClass == null || carClass.isEmpty()) ? carDAO.getAllNotDeletedByDateRange(dateStart, dateEnd, page, itemsPerPage) :
                                                             carDAO.getAllNotDeletedByDateRangeAndClass(carClass, dateStart, dateEnd, page, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while getting cars by date range", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return cars;

    }

    @Override
    public int getCarsPagesCount(FindCarsDTO findCarsDTO, int itemsPerPage) throws ServiceException {

        String carClass = findCarsDTO.getCarClass();

        Date dateStart = DateParser.parse(findCarsDTO.getDateStart());
        Date dateEnd = DateParser.parse(findCarsDTO.getDateEnd());

        int pagesCount;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            int carsCount = (carClass == null || carClass.isEmpty()) ? carDAO.getTotalCountByDateRange(dateStart, dateEnd) :
                                                                       carDAO.getTotalCountByDateRangeAndClass(carClass, dateStart, dateEnd);
            pagesCount = PageCounter.countPages(carsCount, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while getting count of cars pages", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return pagesCount;

    }

    public Car getCar(int carID) throws ServiceException {

        Car car;

        TransactionHelper transactionHelper = null;

        try {

            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO, reviewDAO);

            car = carDAO.getByID(carID);
            car.setReviewList(reviewDAO.getAllByCarID(carID));

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Could not find the car", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return car;
    }

    @Override
    public void addCar(Car car) throws ServiceException {

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carDAO.add(car);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while adding car!", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    @Override
    public void editCar(Car car) throws ServiceException {

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carDAO.update(car);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while editing car!", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    @Override
    public void deleteCar(int carID) throws ServiceException {

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carDAO.delete(carID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while deleting car", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    @Override
    public void addPhoto(int carID, String filename) throws ServiceException {

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carDAO.addPhoto(carID, filename);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("error while adding car photo", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    @Override
    public void deletePhoto(int photoID) throws ServiceException {

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO);

            carDAO.deletePhoto(photoID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while deleting car photo", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

}
