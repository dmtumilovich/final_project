package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.OrderDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;
import by.epam.rentacar.dao.util.constant.DBSchema;
import by.epam.rentacar.domain.dto.UserOrderDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends OrderDAO {

    @Override
    public List<Order> getOrderList() throws DAOException {
        List<Order> orderList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_ALL_ORDERS);
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
    public List<Order> getOrdersByStatusId(int statusID) throws DAOException {

        List<Order> orders = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(DBQueries.GET_ORDERS_BY_STATUS_ID);
            statement.setInt(1, statusID);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = ResultSetParser.createOrder(resultSet);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while getting orders by id", e);
        }

        return orders;

    }

    @Override
    public List<Order> getRejectedOrders() throws DAOException {
        return null;
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
    public List<UserOrderDTO> getUserOrders(int userID) throws DAOException {

        List<UserOrderDTO> orderList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_USER_ORDERS);
            statement.setInt(1, userID);

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
            e.printStackTrace();
        }

        return orderList;

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
}
