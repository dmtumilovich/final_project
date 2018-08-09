package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.*;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    void makeOrder(MakeOrderDTO makeOrderDTO) throws ServiceException;
    OrderingInfo getOrderingInfo(int carID, int userID) throws ServiceException;
    UserOrderDTO getUserOrder(int orderID) throws ServiceException;
    UserOrdersDTO getUserOrders(int userID) throws ServiceException;
    OrderInfoDTO getOrderInfo(int orderID) throws ServiceException;
    List<Order> getAllOrders() throws ServiceException;
    List<Order> getOrdersByStatus(String status) throws ServiceException;
    List<Order> getOrdersByStatus(Order.Status status) throws ServiceException;
    List<Order> getWaitingOrders() throws ServiceException;
    List<Order> getRejectedOrders() throws ServiceException;
    void updateStatus(int orderID, Order.Status status)  throws ServiceException;
    void updateStatus(int orderID, String status) throws ServiceException;

}
