package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.entity.Order;

import java.util.List;

public abstract class OrderDAO extends AbstractDAO<Order> {

    public abstract List<Order> getAllByStatusId(int statusID, int page, int itemsPerPage) throws DAOException;
    public abstract int getTotalCountByStatusID(int statusID) throws DAOException;

    public abstract UserOrderDTO getUserOrder(int orderID) throws DAOException;
    public abstract List<UserOrderDTO> getUserOrders(int userID, int page, int itemsPerPage) throws DAOException;
    public abstract int getUserOrdersCount(int userID) throws DAOException;
    public abstract OrderInfoDTO getOrderAndUserInfo(int orderID) throws DAOException;

    public abstract int getStatusIdByName(Order.Status status) throws DAOException;
    public abstract void updateStatus(int orderID, int statusID) throws DAOException;

}
