package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.*;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders(int page, int itemsPerPage) throws ServiceException;
    List<Order> getOrdersByStatus(String status, int page, int itemsPerPage) throws ServiceException;
    List<Order> getOrdersByStatus(Order.Status status, int page, int itemsPerPage) throws ServiceException;
    int getAllOrdersPagesCount(int itemsPerPage) throws ServiceException;
    int getOrdersPagesCountByStatus(int itemsPerPage, String status) throws ServiceException;
    int getOrdersPagesCountByStatus(int itemsPerPage, Order.Status status) throws ServiceException;

    OrderInfoDTO getOrderInfo(int orderID) throws ServiceException;

    void makeOrder(MakeOrderDTO makeOrderDTO) throws ServiceException;
    OrderingInfo getBookingInfo(int carID, int userID, String dateStart, String dateEnd) throws ServiceException;

    UserOrderDTO getUserOrder(int orderID) throws ServiceException;
    UserOrdersDTO getUserOrders(int userID, int page, int itemsPerPage) throws ServiceException;

    int getUserOrdersPagesCount(int userID, int itemsPerPage) throws ServiceException;

    void updateStatus(int orderID, String status) throws ServiceException;
    void updateStatus(int orderID, Order.Status status)  throws ServiceException;

    void confirmOrder(int orderID) throws ServiceException;

}
