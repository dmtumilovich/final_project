package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.domain.dto.CarInfoDTO;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.List;

public interface AdminService {

    List<CarItemDTO> getCarList() throws ServiceException;
    CarInfoDTO getCarInfo(int carID) throws ServiceException;
    boolean addCar(AddCarDTO addCarDTO) throws ServiceException;
    boolean editCar(Car car) throws ServiceException;
    boolean deleteCar(int carID) throws ServiceException;

    List<Order> getOrderList() throws ServiceException;
    OrderInfoDTO getOrderInfo(int orderID) throws ServiceException;
    void confirmOrder(int orderID) throws ServiceException;
    void rejectOrder(int orderID) throws ServiceException;

}
