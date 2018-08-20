package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.entity.Order;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Defines abstract methods to work with orders in the database table.
 */
public abstract class OrderDAO extends AbstractDAO<Order> {

    /**
     * Gets orders by it's status id according to the page and items per page.
     *
     * @param statusID is the id of the status to get orders.
     * @param page is the current page.
     * @param itemsPerPage is the value to limit orders per page.
     * @return the list of {@link Order}.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract List<Order> getAllByStatusId(int statusID, int page, int itemsPerPage) throws DAOException;

    /**
     * Gets total count of all orders according to the order's status id.
     *
     * @param statusID is the id of the status to get total count.
     * @return the number of orders.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract int getTotalCountByStatusID(int statusID) throws DAOException;

    /**
     * Gets order from the database table according to order id.
     *
     * @param orderID is the id of the order.
     * @return the {@link UserOrderDTO} object if it was founded or null if not.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract UserOrderDTO getUserOrder(int orderID) throws DAOException;

    /**
     * Gets orders according to the user id, page and items per page.
     *
     * @param userID is the id of the user.
     * @param page is the current page.
     * @param itemsPerPage is the value to limit orders per page.
     * @return the list of {@link UserOrderDTO}.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract List<UserOrderDTO> getUserOrders(int userID, int page, int itemsPerPage) throws DAOException;

    /**
     * Gets total count of user orders according to the user id.
     *
     * @param userID is the id of the user.
     * @return the number of orders.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract int getUserOrdersCount(int userID) throws DAOException;

    /**
     * Gets order info from the database, that include information about the order, car and user. (See {@link OrderInfoDTO}.
     *
     * @param orderID is the id of the order.
     * @return the {@link OrderInfoDTO} object if it was founded or null if not.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract OrderInfoDTO getOrderAndUserInfo(int orderID) throws DAOException;

    /**
     * Gets id of the order's status from the database table.
     *
     * @param status is the order status.
     * @return id of this status.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract int getStatusIdByName(Order.Status status) throws DAOException;

    /**
     * Updates order status in the database table.
     *
     * @param orderID is the id of the order.
     * @param statusID is the id of the new status.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void updateStatus(int orderID, int statusID) throws DAOException;

    /**
     * Updates order's status to {@link by.epam.rentacar.domain.entity.Order.Status#REJECTED} of orders, thad intersecting selected date range.
     *
     * @param orderID is the id of the order.
     * @param carID is the id of the car.
     * @param dateStart is the start date.
     * @param dateEnd is the end date.
     * @throws DAOException if {@link SQLException} happens.
     */
    public abstract void rejectOrdersIntersectingDateRange(int orderID, int carID, Date dateStart, Date dateEnd) throws DAOException;

}
