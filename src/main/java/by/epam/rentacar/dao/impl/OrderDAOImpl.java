package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.OrderDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;
import by.epam.rentacar.dao.util.constant.DBSchema;
import by.epam.rentacar.domain.dto.OrderingInfo;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl extends OrderDAO {

    @Override
    public List<Order> getAllOrders(int page, int itemsPerPage) throws DAOException {

        List<Order> orderList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(DBQueries.GET_ALL_ORDERS);
            statement.setInt(1, itemsPerPage);
            statement.setInt(2, calculateOffset(page, itemsPerPage));
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = ResultSetParser.createOrder(resultSet);
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public List<Order> getOrdersByStatusId(int statusID, int page, int itemsPerPage) throws DAOException {

        List<Order> orders = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(DBQueries.GET_ORDERS_BY_STATUS_ID);
            statement.setInt(1, statusID);
            statement.setInt(2, itemsPerPage);
            statement.setInt(3, calculateOffset(page, itemsPerPage));

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = ResultSetParser.createOrder(resultSet);
                orders.add(order);
            }

        } catch (SQLException e) {
            throw new DAOException("Error while getting orders by id", e);
        }

        return orders;

    }

    @Override
    public int getTotalCount() throws DAOException {

        int ordersCount = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT COUNT(id_order) AS orders_count\n" +
                                                        "FROM order_list");

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ordersCount = resultSet.getInt("orders_count");
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting number of all orders!", e);
        }

        return ordersCount;

    }

    @Override
    public int getTotalCountByStatusID(int statusID) throws DAOException {

        int ordersCount = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT COUNT(id_order) AS orders_count\n" +
                                                        "FROM order_list\n" +
                                                        "WHERE id_status = ?;");
            statement.setInt(1, statusID);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ordersCount = resultSet.getInt("orders_count");
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting number of orders by status id", e);
        }

        return ordersCount;

    }


    @Override
    public UserOrderDTO getUserOrder(int orderID) throws DAOException {

        UserOrderDTO userOrderDTO = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_USER_ORDER);
            statement.setInt(1, orderID);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOrderDTO = new UserOrderDTO();

                Order order = ResultSetParser.createOrder(resultSet);
                Car car = ResultSetParser.createCar(resultSet);

                userOrderDTO.setOrder(order);
                userOrderDTO.setCar(car);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userOrderDTO;

    }

    @Override
    public List<UserOrderDTO> getUserOrders(int userID, int page, int itemsPerPage) throws DAOException {

        List<UserOrderDTO> orderList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_USER_ORDERS);
            statement.setInt(1, userID);
            statement.setInt(2, itemsPerPage);
            statement.setInt(3, calculateOffset(page, itemsPerPage));

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = ResultSetParser.createOrder(resultSet);
                Car car = ResultSetParser.createCar(resultSet);

                UserOrderDTO userOrderDTO = new UserOrderDTO();
                userOrderDTO.setOrder(order);
                userOrderDTO.setCar(car);

                orderList.add(userOrderDTO);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting user orders", e);
        }

        return orderList;

    }

    @Override
    public int getUserOrdersCount(int userID) throws DAOException {

        int ordersCount = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT COUNT(id_order) AS orders_count\n" +
                                                        "FROM order_list\n" +
                                                        "WHERE id_user = ?");
            statement.setInt(1, userID);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ordersCount = resultSet.getInt("orders_count");
            }

        } catch (SQLException e) {
            throw new DAOException("Error while getting the number of user's orders", e);
        }

        return ordersCount;

    }

    @Override
    public void makeOrder(Order order) throws DAOException {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DBQueries.INSERT_ORDER);
            statement.setInt(1, order.getUserID());
            statement.setInt(2, order.getCarID());
            Timestamp dateStart = new Timestamp(order.getDateStart().getTime());
            Timestamp dateEnd = new Timestamp(order.getDateEnd().getTime());
            statement.setTimestamp(3, dateStart);
            statement.setTimestamp(4, dateEnd);
            statement.setDouble(5, order.getTotalPrice());
            statement.setInt(6, 3);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("error while inserting order", e);
        }

    }

    @Override
    public int getStatusIdByName(Order.Status status) throws DAOException {

        int statusID = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT id_status FROM order_status WHERE status = ?");
            statement.setString(1, status.toString().toLowerCase());

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                statusID = resultSet.getInt(DBSchema.OrderStatusTable.ID_STATUS);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while getting status id", e);
        }

        return statusID;

    }

    @Override
    public void updateStatus(int orderID, int statusID) throws DAOException {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("UPDATE order_list\n" +
                                                        "SET id_status = ?\n" +
                                                        "WHERE id_order = ?");
            statement.setInt(1, statusID);
            statement.setInt(2, orderID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while updating status", e);
        }

    }

    @Override
    public List<OrderingInfo.DateRange> getBusyDates(int carID) throws DAOException {

        List<OrderingInfo.DateRange> busyDates = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT order_list.date_start, order_list.date_end\n" +
                                                        "FROM order_list\n" +
                                                        "WHERE id_car = ?");
            statement.setInt(1, carID);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Timestamp dateStartTS = resultSet.getTimestamp(DBSchema.OrderListTable.DATE_START);
                Timestamp dateEndTS = resultSet.getTimestamp(DBSchema.OrderListTable.DATE_END);

                Date dateStart = new Date(dateEndTS.getTime());
                Date dateEnd = new Date(dateStart.getTime());

                OrderingInfo.DateRange dateRange = new OrderingInfo.DateRange(dateStart ,dateEnd);
                busyDates.add(dateRange);
            }

        } catch (SQLException e) {
            throw new DAOException("Error while getting busy dates!", e);
        }

        return busyDates;

    }
}
