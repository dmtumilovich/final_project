package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.entity.Order;

import java.util.List;

public abstract class OrderDAO extends AbstractDAO {

    public abstract List<Order> getOrderList() throws DAOException;
    public abstract UserOrderDTO getUserOrder(int orderID) throws DAOException;
    public abstract List<UserOrderDTO> getUserOrders(int userID) throws DAOException;
    public abstract void makeOrder(Order order) throws DAOException;

}
