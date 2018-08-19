package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.*;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.List;

/**
 * The interface defines methods to work with orders.
 */
public interface OrderService {

    /**
     * Gets orders according to the page and items per page.
     *
     * @param page is the current page.
     * @param itemsPerPage is the number of items to get.
     * @return the list of an {@link Order} objects.
     * @throws ServiceException
     */
    List<Order> getAllOrders(int page, int itemsPerPage) throws ServiceException;

    /**
     * Gets orders according to the order's status, page and items per page.
     *
     * @param status is the {@link String} value of order's status.
     * @param page is the current page.
     * @param itemsPerPage is the number of items to get.
     * @return the list of an {@link Order} objects.
     * @throws ServiceException
     */
    List<Order> getOrdersByStatus(String status, int page, int itemsPerPage) throws ServiceException;

    /**
     * Gets orders according to the order's status, page and items per page.
     *
     * @param status is the {@link Order.Status} value of order's status.
     * @param page is the current page.
     * @param itemsPerPage is the number of items to get.
     * @return the list of an {@link Order} objects.
     * @throws ServiceException
     */
    List<Order> getOrdersByStatus(Order.Status status, int page, int itemsPerPage) throws ServiceException;

    /**
     * Gets count of pages according to items per page.
     *
     * @param itemsPerPage is the number of items per one page.
     * @return the number of pages.
     * @throws ServiceException
     */
    int getAllOrdersPagesCount(int itemsPerPage) throws ServiceException;

    /**
     * Gets count of pages according to the status and items per page.
     *
     * @param status is the {@link String} value of order's status.
     * @param itemsPerPage is the number of items per one page.
     * @return the number of pages.
     * @throws ServiceException
     */
    int getOrdersPagesCountByStatus(int itemsPerPage, String status) throws ServiceException;

    /**
     * Gets count of pages according to the status and items per page.
     *
     * @param status is the {@link Order.Status} value of order's status.
     * @param itemsPerPage is the number of items per one page.
     * @return the number of pages.
     * @throws ServiceException
     */
    int getOrdersPagesCountByStatus(int itemsPerPage, Order.Status status) throws ServiceException;

    /**
     * Gets information about the order.
     *
     * @param orderID is id of the order.
     * @return an {@link OrderInfoDTO} object.
     * @throws ServiceException
     */
    OrderInfoDTO getOrderInfo(int orderID) throws ServiceException;

    /**
     * Checks if the ordering data is valid and adds new order with status {@link Order.Status#AWAITS}.
     *
     * @param makeOrderDTO is an {@link MakeOrderDTO} object, containing ordering data.
     * @throws ServiceException
     */
    void makeOrder(MakeOrderDTO makeOrderDTO) throws ServiceException;

    /**
     * Gets full information about booking, such as information about the car, date range and etc.
     *
     * @param carID is the id of the car.
     * @param userID is the id of the user.
     * @param dateStart is the beginning of the date interval.
     * @param dateEnd is the end of the date interval.
     * @return an {@link OrderingInfo} object.
     * @throws ServiceException
     */
    OrderingInfo getBookingInfo(int carID, int userID, String dateStart, String dateEnd) throws ServiceException;

    /**
     * Gets information about order for the user.
     *
     * @param orderID is the id of the order.
     * @return an {@link UserOrderDTO} object.
     * @throws ServiceException
     */
    UserOrderDTO getUserOrder(int orderID) throws ServiceException;

    /**
     * Gets the list of user orders according to the it's id, page and number of items per page.
     * @param userID is the id of the user.
     * @param page is the current page.
     * @param itemsPerPage is the number of items per page to get.
     * @return the list of {@link UserOrderDTO} objects.
     * @throws ServiceException
     */
    List<UserOrderDTO> getUserOrders(int userID, int page, int itemsPerPage) throws ServiceException;


    /**
     * Gets the count of pages of user's orders.
     *
     * @param userID is the id of the user.
     * @param itemsPerPage is the number of items per one page.
     * @return the number of pages.
     * @throws ServiceException
     */
    int getUserOrdersPagesCount(int userID, int itemsPerPage) throws ServiceException;


    /**
     * Updates the status of the order.
     *
     * @param orderID is the order id.
     * @param status is the {@link String} value of the order's status.
     * @throws ServiceException
     */
    void updateStatus(int orderID, String status) throws ServiceException;

    /**
     * Updates the status of the order.
     *
     * @param orderID is the order id.
     * @param status is the new value of order's status.
     * @throws ServiceException
     */
    void updateStatus(int orderID, Order.Status status)  throws ServiceException;


    /**
     * Confirms order. At first checks if there any other orders on this car, that intersecting
     * given order's date range. If there is, then rejecting those orders. After that sets order's
     * status to {@link Order.Status#CONFIRMED}.
     *
     * @param orderID is the order id.
     * @throws ServiceException
     */
    void confirmOrder(int orderID) throws ServiceException;

}
