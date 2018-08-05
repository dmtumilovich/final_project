package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.MakeOrderDTO;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.dto.UserOrdersDTO;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    void makeOrder(MakeOrderDTO makeOrderDTO) throws ServiceException;
    OrderingInfo getOrderingInfo(int carID, int userID) throws ServiceException;
    UserOrderDTO getUserOrder(int orderID) throws ServiceException;
    UserOrdersDTO getUserOrders(int userID) throws ServiceException;

}
