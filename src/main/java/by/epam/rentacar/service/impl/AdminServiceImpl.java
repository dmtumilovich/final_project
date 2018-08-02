package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.AdminDAO;
import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.AdminDAOImpl;
import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.domain.dto.CarInfoDTO;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public List<CarItemDTO> getCarList() throws ServiceException {

        List<CarItemDTO> carList = new ArrayList<>();

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            carList =  adminDAO.getCarList();

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction(); //проверка на нулл?
        }

        return carList;
    }

    @Override
    public CarInfoDTO getCarInfo(int carID) throws ServiceException {

        CarInfoDTO carInfoDTO = null;

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            carInfoDTO = adminDAO.getCarInfo(carID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction(); //проверка на нулл?
        }

        return carInfoDTO;
    }

    @Override
    public boolean addCar(AddCarDTO addCarDTO) throws ServiceException {

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            adminDAO.addCar(addCarDTO);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction(); //проверка на нулл?
        }

        return true;

    }

    @Override
    public boolean editCar(Car car) throws ServiceException {

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            adminDAO.editCar(car);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }

        return true;

    }

    @Override
    public boolean deleteCar(int carID) throws ServiceException {

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            adminDAO.deleteCar(carID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }

        return true;

    }

    @Override
    public List<Order> getOrderList() throws ServiceException {

        List<Order> orderList = new ArrayList<>();

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {

            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            orderList = adminDAO.getOrderList();

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction(); //проверка на нулл?
        }

        return orderList;

    }

    @Override
    public OrderInfoDTO getOrderInfo(int orderID) throws ServiceException {

        OrderInfoDTO orderInfoDTO = null;

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            orderInfoDTO = adminDAO.getOrderInfo(orderID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }

        return orderInfoDTO;

    }

    @Override
    public void confirmOrder(int orderID) throws ServiceException {

        //валидация заказа должна присутствовать

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            adminDAO.confirmOrder(orderID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }

    }

    @Override
    public void rejectOrder(int orderID) throws ServiceException {

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            adminDAO.rejectOrder(orderID);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }

    }
}
