package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.domain.dto.CarInfoDTO;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;

import java.util.List;

public abstract class AdminDAO extends AbstractDAO {

    public abstract List<CarItemDTO> getCarList() throws DAOException;
    public abstract CarInfoDTO getCarInfo(int carID) throws DAOException;
    public abstract boolean addCar(AddCarDTO addCarDTO) throws DAOException;
    public abstract boolean editCar(Car car) throws DAOException;
    public abstract boolean deleteCar(int carID) throws DAOException;

    public abstract List<Order> getOrderList() throws DAOException;
    public abstract OrderInfoDTO getOrderInfo(int orderID) throws DAOException;
    public abstract void confirmOrder(int orderID) throws DAOException;
    public abstract void rejectOrder(int orderID) throws DAOException;

}
