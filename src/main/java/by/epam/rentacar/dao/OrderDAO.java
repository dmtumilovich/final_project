package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.entity.Order;

import java.util.List;

public abstract class OrderDAO extends AbstractDAO {

    public abstract List<Order> getOrderList() throws DAOException;
    public abstract List<Order> getOrdersByStatusId(int statusID) throws DAOException;
    public abstract List<Order> getRejectedOrders() throws DAOException;
    public abstract UserOrderDTO getUserOrder(int orderID) throws DAOException;
    public abstract List<UserOrderDTO> getUserOrders(int userID) throws DAOException;
    public abstract void makeOrder(Order order) throws DAOException;
    public abstract int getStatusIdByName(Order.Status status) throws DAOException;
    public abstract void updateStatus(int orderID, int statusID) throws DAOException;

}
