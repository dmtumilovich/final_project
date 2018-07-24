package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.AdminDAO;
import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.AdminDAOImpl;
import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.domain.dto.CarInfoDTO;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public List<CarItemDTO> getCarList() throws ServiceException {

        List<CarItemDTO> carList = new ArrayList<>();

        AdminDAO adminDAO = new AdminDAOImpl();

        try {
            TransactionHelper transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            carList =  adminDAO.getCarList();

            transactionHelper.endTransaction();
            transactionHelper.commit();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return carList;
    }

    @Override
    public CarInfoDTO getCarInfo(int carID) throws ServiceException {

        CarInfoDTO carInfoDTO = null;

        AdminDAO adminDAO = new AdminDAOImpl();

        try {
            TransactionHelper transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            carInfoDTO = adminDAO.getCarInfo(carID);

            transactionHelper.endTransaction();
            transactionHelper.commit();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return carInfoDTO;
    }

    @Override
    public boolean addCar(AddCarDTO addCarDTO) throws ServiceException {

        AdminDAO adminDAO = new AdminDAOImpl();

        try {
            TransactionHelper transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            adminDAO.addCar(addCarDTO);

            transactionHelper.endTransaction();
            transactionHelper.commit();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean editCar(Car car) throws ServiceException {

        AdminDAO adminDAO = new AdminDAOImpl();

        try {
            TransactionHelper transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            adminDAO.editCar(car);

            transactionHelper.endTransaction();
            transactionHelper.commit();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean deleteCar(int carID) throws ServiceException {

        AdminDAO adminDAO = new AdminDAOImpl();

        try {
            TransactionHelper transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            adminDAO.deleteCar(carID);

            transactionHelper.endTransaction();
            transactionHelper.commit();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return true;

    }
}
