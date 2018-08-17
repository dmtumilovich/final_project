package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.*;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.*;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.exception.CarNotAvailableException;
import by.epam.rentacar.service.exception.InvalidDateRangeException;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.service.util.DateParser;
import by.epam.rentacar.service.util.PageCounter;
import by.epam.rentacar.service.validation.Validator;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {

    private static final DAOFactory daoFactory = DAOFactory.getInstance();

    private static final OrderDAO orderDAO = daoFactory.getOrderDAO();
    private static final CarDAO carDAO = daoFactory.getCarDAO();
    private static final UserDAO userDAO = daoFactory.getUserDAO();

    private static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);

    @Override
    public void makeOrder(MakeOrderDTO makeOrderDTO) throws ServiceException {

        Date dateStart = DateParser.parse(makeOrderDTO.getDateStart());
        Date dateEnd = DateParser.parse(makeOrderDTO.getDateEnd());

        if(!Validator.isDateRangeValid(dateStart, dateEnd)) {
            throw new InvalidDateRangeException("Invalid date range!");
        }

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO, orderDAO);

            int carID = makeOrderDTO.getCarID();
            if(!orderDAO.isCarAvailable(carID, dateStart, dateEnd)) {
                throw new CarNotAvailableException("Car is busy for this date range");
            }

            double carPrice = carDAO.getPriceByCarID(makeOrderDTO.getCarID());
            double totalCost = calculateTotalCost(carPrice, dateStart, dateEnd);

            System.out.println("TOTAL COST: " + totalCost);

            Order order = new Order();
            order.setUserID(makeOrderDTO.getUserID());
            order.setCarID(carID);
            order.setDateStart(dateStart); //проверка на нулл?
            order.setDateEnd(dateEnd);
            order.setTotalPrice(totalCost);
            order.setStatus(Order.Status.AWAITS);

            orderDAO.add(order);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("error while making an order", e);
        } finally {
            transactionHelper.endTransaction();
        }


    }

    @Override
    public OrderingInfo getBookingInfo(int carID, int userID, String dateStartStr, String dateEndStr) throws ServiceException {

        Date dateStart = DateParser.parse(dateStartStr);
        Date dateEnd = DateParser.parse(dateEndStr);

        OrderingInfo orderingInfo = new OrderingInfo();

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO, carDAO, orderDAO);

            Car car = carDAO.getByID(carID);
            User user = userDAO.getByID(userID);

            //calculate days
            int numberOfDays = (int)Math.ceil((dateEnd.getTime() - dateStart.getTime()) / (1000*60*60*24));
            //calculate total cost
            double totalCost = numberOfDays * car.getPrice();

            if (user.getName() == null || user.getName().isEmpty()
                    || user.getSurname() == null || user.getSurname().isEmpty()
                    || user.getPassport() == null || user.getPassport().isEmpty()
                    || user.getPhone() == null || user.getPhone().isEmpty()) {
                orderingInfo.setAllUserData(false);
            }

            orderingInfo.setCar(car);
            orderingInfo.setUser(user);
            orderingInfo.setNumberOfDays(numberOfDays);
            orderingInfo.setTotalCost(totalCost);

            transactionHelper.commit();
        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while getting ordering info", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return orderingInfo;

    }

    @Override
    public UserOrderDTO getUserOrder(int orderID) throws ServiceException {

        UserOrderDTO userOrderDTO;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            userOrderDTO = orderDAO.getUserOrder(orderID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("error while getting user order info", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return userOrderDTO;

    }

    @Override
    public List<UserOrderDTO> getUserOrders(int userID, int page, int itemsPerPage) throws ServiceException {

        List<UserOrderDTO> userOrderList = null;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            userOrderList = orderDAO.getUserOrders(userID, page, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
        } finally {
            transactionHelper.endTransaction();
        }

        return userOrderList;

    }

    @Override
    public int getUserOrdersPagesCount(int userID, int itemsPerPage) throws ServiceException {

        int pagesCount;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int ordersCount = orderDAO.getUserOrdersCount(userID);
            pagesCount = PageCounter.countPages(ordersCount, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while getting count of user orders pages", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return pagesCount;

    }

    @Override
    public List<Order> getAllOrders(int page, int itemsPerPage) throws ServiceException {

        List<Order> orders;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            orders = orderDAO.getAll(page, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            throw new ServiceException("Error while getting all orders", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return orders == null ? Collections.<Order>emptyList() : orders;

    }

    @Override
    public List<Order> getOrdersByStatus(String statusName, int page, int itemsPerPage) throws ServiceException {

        Order.Status status = getStatusFromString(statusName);
        return getOrdersByStatus(status, page, itemsPerPage);

    }

    @Override
    public List<Order> getOrdersByStatus(Order.Status status, int page, int itemsPerPage) throws ServiceException {

        List<Order> orders;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int statusID = orderDAO.getStatusIdByName(status);
            orders = orderDAO.getAllByStatusId(statusID, page, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while getting orders by status", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return orders == null ? Collections.<Order>emptyList() : orders;

    }

    @Override
    public int getAllOrdersPagesCount(int itemsPerPage) throws ServiceException {

        int pagesCount;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int ordersCount = orderDAO.getTotalCount();
            pagesCount = PageCounter.countPages(ordersCount, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while counting pages of all orders!", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return pagesCount;

    }

    @Override
    public int getOrdersPagesCountByStatus(int itemsPerPage, String statusStr) throws ServiceException {

        if (statusStr == null || statusStr.isEmpty()) {
            return getAllOrdersPagesCount(itemsPerPage);
        }

        Order.Status status = getStatusFromString(statusStr);
        return getOrdersPagesCountByStatus(itemsPerPage, status);

    }

    @Override
    public int getOrdersPagesCountByStatus(int itemsPerPage, Order.Status status) throws ServiceException {

        int pagesCount;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int statusID = orderDAO.getStatusIdByName(status);
            int ordersCount = orderDAO.getTotalCountByStatusID(statusID);

            pagesCount = PageCounter.countPages(ordersCount, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while counting orders page by status id", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return pagesCount;

    }

    @Override
    public OrderInfoDTO getOrderInfo(int orderID) throws ServiceException {

        OrderInfoDTO orderInfoDTO;

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            orderInfoDTO = orderDAO.getOrderAndUserInfo(orderID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while getting order and car info!", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return orderInfoDTO;

    }

    @Override
    public void updateStatus(int orderID, String statusName) throws ServiceException {

        Order.Status status = null;

        try {
            status = Order.Status.valueOf(statusName.toUpperCase());
        } catch (IllegalArgumentException e) {
            //кинуть IncorrectStatusException
        }

        updateStatus(orderID, status);
    }

    @Override
    public void updateStatus(int orderID, Order.Status status) throws ServiceException {

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int statusID = orderDAO.getStatusIdByName(status);
            orderDAO.updateStatus(orderID, statusID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while updating status", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    @Override
    public void confirmOrder(int orderID) throws ServiceException {

        //добавить валидацию(если есть другие заказы на эту дату)

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int statusConfirmedID = orderDAO.getStatusIdByName(Order.Status.CONFIRMED);
            orderDAO.updateStatus(orderID, statusConfirmedID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while confirming order!", e);
        } finally {
            transactionHelper.endTransaction();
        }

    }

    private double calculateTotalCost(double price, Date dateStart, Date dateEnd) {
        return calculateTotalCost(price, calculateCountOfDays(dateStart, dateEnd));
    }

    private double calculateTotalCost(double price, int numberOfDays) {
        return price * numberOfDays;
    }

    private int calculateCountOfDays(Date dateStart, Date dateEnd) {
        return (int) Math.ceil((dateEnd.getTime() - dateStart.getTime()) / (DAY_IN_MILLIS));
    }

    private Order.Status getStatusFromString(String statusName) {

        Order.Status status = null;

        try {
            status = Order.Status.valueOf(statusName.toUpperCase());
        } catch (IllegalArgumentException e) {
            //кинуть IncorrectStatusException
            System.out.println("INCORRECT STATUS");
        }

        return status;

    }
}
