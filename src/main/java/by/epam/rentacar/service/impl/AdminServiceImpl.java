package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.AdminDAO;
import by.epam.rentacar.dao.DAOFactory;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.domain.dto.CarInfoDTO;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.AdminService;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    public static final AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();

    @Override
    public List<CarItemDTO> getCarList() throws ServiceException {

        List<CarItemDTO> carList = new ArrayList<>();

        try {
            carList =  adminDAO.getCarList();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return carList;
    }

    @Override
    public CarInfoDTO getCarInfo(int carID) throws ServiceException {

        CarInfoDTO carInfoDTO = null;

        try {
            carInfoDTO = adminDAO.getCarInfo(carID);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return carInfoDTO;
    }

    @Override
    public boolean addCar(AddCarDTO addCarDTO) throws ServiceException {

        try {
            adminDAO.addCar(addCarDTO);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean editCar(Car car) throws ServiceException {

        try {
            adminDAO.editCar(car);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean deleteCar(int carID) throws ServiceException {

        try {
            adminDAO.deleteCar(carID);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return true;

    }
}
