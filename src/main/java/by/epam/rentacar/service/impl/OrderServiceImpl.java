package by.epam.rentacar.service.impl;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.dao.OrderDAO;
import by.epam.rentacar.dao.TransactionHelper;
import by.epam.rentacar.dao.UserDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.dao.impl.OrderDAOImpl;
import by.epam.rentacar.dao.impl.UserDAOImpl;
import by.epam.rentacar.domain.dto.MakeOrderDTO;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.dto.UserOrdersDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.domain.entity.User;
import by.epam.rentacar.service.OrderService;
import by.epam.rentacar.service.exception.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

    @Override
    public void makeOrder(MakeOrderDTO makeOrderDTO) throws ServiceException {

        Date dateStart = null;
        Date dateEnd = null;

        try {
            dateStart = dateFormat.parse(makeOrderDTO.getDateStart());
            dateEnd = dateFormat.parse(makeOrderDTO.getDateEnd());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ServiceException("Error occurred while parsing date");
        }

        CarDAO carDAO = new CarDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(carDAO, orderDAO);

            double carPrice = carDAO.getPriceByCarID(makeOrderDTO.getCarID());

            System.out.println("CAR PRICE: " + carPrice);

            Order order = new Order();
            order.setUserID(makeOrderDTO.getUserID());
            order.setCarID(makeOrderDTO.getCarID());
            order.setDateStart(dateStart); //проверка на нулл?
            order.setDateEnd(dateEnd);
            order.setTotalPrice(carPrice);
            order.setStatus(Order.OrderStatus.AWAITS);

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
    public OrderingInfo getOrderingInfo(int carID, int userID) throws ServiceException {

        UserDAO userDAO = new UserDAOImpl();
        CarDAO carDAO = new CarDAOImpl();

        OrderingInfo orderingInfo = new OrderingInfo();

        TransactionHelper transactionHelper = null;

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(userDAO, carDAO);

            Car car = carDAO.getCarByID(carID);
            User user = userDAO.getUserById(userID);

            if (user.getName() == null || user.getName().isEmpty()
                    || user.getSurname() == null || user.getSurname().isEmpty()
                    || user.getPassport() == null || user.getPassport().isEmpty()
                    || user.getPhone() == null || user.getPhone().isEmpty()) {
                orderingInfo.setAllUserData(false);
            }

            orderingInfo.setCar(car);
            orderingInfo.setUser(user);

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
    public UserOrdersDTO getUserOrders(int userID) throws ServiceException {

        UserOrdersDTO userOrdersDTO = new UserOrdersDTO();

        TransactionHelper transactionHelper = null;
        OrderDAO orderDAO = new OrderDAOImpl();

        try {
            transactionHelper = new TransactionHelper();
            transactionHelper.beginTransaction(orderDAO);

            List<UserOrderDTO> userOrderList = orderDAO.getUserOrders(userID);
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
}
