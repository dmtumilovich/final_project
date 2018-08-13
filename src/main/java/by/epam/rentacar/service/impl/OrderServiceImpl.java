package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.*;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.AdminDAOImpl;
import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.dao.impl.OrderDAOImpl;
import by.epam.rentacar.dao.impl.UserDAOImpl;
import by.epam.rentacar.domain.dto.*;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.exception.InvalidDateRangeException;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.service.util.PageCounter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Override
    public void makeOrder(MakeOrderDTO makeOrderDTO) throws ServiceException {

        Date dateStart = null;
        Date dateEnd = null;

        try {
            dateStart = dateFormat.parse(makeOrderDTO.getDateStart());
            dateEnd = dateFormat.parse(makeOrderDTO.getDateEnd());
        } catch (ParseException e) {
            throw new ServiceException("Error occurred while parsing date", e);
        }

        if (dateStart.after(dateEnd) || (dateEnd.getTime() - dateStart.getTime() < 24*60*60*1000)) {
            throw new InvalidDateRangeException("invalid date range");
        }

        CarDAO carDAO = new CarDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO, orderDAO);

            double carPrice = carDAO.getPriceByCarID(makeOrderDTO.getCarID());
            //calculate days
            int numberOfDays = (int)Math.ceil((dateEnd.getTime() - dateStart.getTime()) / (1000*60*60*24));
            //calculate total cost
            double totalCost = numberOfDays * carPrice;

            System.out.println("CAR PRICE: " + carPrice);

            Order order = new Order();
            order.setUserID(makeOrderDTO.getUserID());
            order.setCarID(makeOrderDTO.getCarID());
            order.setDateStart(dateStart); //проверка на нулл?
            order.setDateEnd(dateEnd);
            order.setTotalPrice(totalCost);
            order.setStatus(Order.Status.AWAITS);

            orderDAO.makeOrder(order);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("error while making an order", e);
        } finally {
            transactionHelper.endTransaction();
        }


    }

    @Override
    public OrderingInfo getBookingInfo(int carID, int userID, String dateStartStr, String dateEndStr) throws ServiceException {

        Date dateStart = null;
        Date dateEnd = null;

        try {
            dateStart = dateFormat.parse(dateStartStr);
            dateEnd = dateFormat.parse(dateEndStr);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ServiceException("Error occurred while parsing date");
        }

        UserDAO userDAO = new UserDAOImpl();
        CarDAO carDAO = new CarDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();

        OrderingInfo orderingInfo = new OrderingInfo();

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO, carDAO, orderDAO);

            Car car = carDAO.getCarByID(carID);
            User user = userDAO.getUserById(userID);

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
            e.printStackTrace();
            throw new ServiceException("Error while getting ordering info", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return orderingInfo;

    }

    @Override
    public UserOrderDTO getUserOrder(int orderID) throws ServiceException {

        UserOrderDTO userOrderDTO = null;

        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            userOrderDTO = orderDAO.getUserOrder(orderID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("error while getting user order info", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return userOrderDTO;

    }

    @Override
    public UserOrdersDTO getUserOrders(int userID, int page, int itemsPerPage) throws ServiceException {

        UserOrdersDTO userOrdersDTO = new UserOrdersDTO();

        TransactionHelper transactionHelper = null;
        OrderDAO orderDAO = new OrderDAOImpl();

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            List<UserOrderDTO> userOrderList = orderDAO.getUserOrders(userID, page, itemsPerPage);
            userOrdersDTO.setOrderList(userOrderList);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
        } finally {
            transactionHelper.endTransaction();
        }

        return userOrdersDTO;

    }

    @Override
    public int getUserOrdersPagesCount(int userID, int itemsPerPage) throws ServiceException {

        int pagesCount = 0;

        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int ordersCount = orderDAO.getUserOrdersCount(userID);
            pagesCount = PageCounter.getInstance().countPages(ordersCount, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            throw new ServiceException("Error while getting count of user orders pages", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return pagesCount;

    }

    //зачем я его написал?)))
    @Override
    public OrderInfoDTO getOrderInfo(int orderID) throws ServiceException {

        OrderInfoDTO orderInfoDTO = null;

        AdminDAO adminDAO = new AdminDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(adminDAO);

            orderInfoDTO = adminDAO.getOrderInfo(orderID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Error while getting order info!", e);
        } finally {
            transactionHelper.endTransaction();
        }

        return orderInfoDTO;

    }

    @Override
    public List<Order> getAllOrders(int page, int itemsPerPage) throws ServiceException {

        List<Order> orders = null;

        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            orders = orderDAO.getAllOrders(page, itemsPerPage);

            transactionHelper.commit();

        } catch (DAOException e) {
            e.printStackTrace();
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

        List<Order> orders = null;

        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int statusID = orderDAO.getStatusIdByName(status);
            orders = orderDAO.getOrdersByStatusId(statusID, page, itemsPerPage);

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

        int pagesCount = 0;

        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int ordersCount = orderDAO.getTotalCount();
            pagesCount = PageCounter.getInstance().countPages(ordersCount, itemsPerPage);

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

        int pagesCount = 0;

        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int statusID = orderDAO.getStatusIdByName(status);
            int ordersCount = orderDAO.getTotalCountByStatusID(statusID);

            pagesCount = PageCounter.getInstance().countPages(ordersCount, itemsPerPage);

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

        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            int statusID = orderDAO.getStatusIdByName(status);
            orderDAO.updateStatus(orderID, statusID);

            transactionHelper.commit();

        } catch (DAOException e) {
            transactionHelper.rollback();
            e.printStackTrace();
            throw new ServiceException("Error while updating status", e);
        } finally {
            transactionHelper.endTransaction();
        }

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
