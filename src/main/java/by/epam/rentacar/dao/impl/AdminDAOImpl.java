package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.AdminDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;
import by.epam.rentacar.dao.util.constant.DBSchema;
import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.domain.dto.CarInfoDTO;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.domain.dto.OrderInfoDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl extends AdminDAO {

    private static final String COLUMN_COMMENTS_COUNT = "comments_count";

    //переделать и доделать
    @Override
    public List<CarItemDTO> getCarList() throws DAOException {

        List<CarItemDTO> carItemList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(DBQueries.FIND_ALL_CARS_FOR_ADMIN);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarItemDTO carItemDTO = new CarItemDTO();
                carItemDTO.setCarID(resultSet.getInt(DBSchema.CarListTable.ID_CAR));
                carItemDTO.setBrand(resultSet.getString(DBSchema.CarListTable.BRAND));
                carItemDTO.setModel(resultSet.getString(DBSchema.CarListTable.MODEL));
                carItemDTO.setCarClass(resultSet.getString(DBSchema.CarListTable.CLASS));
                carItemDTO.setPrice(resultSet.getDouble(DBSchema.CarListTable.PRICE));
                carItemDTO.setStatus("Available");
                carItemDTO.setCommentsCount(resultSet.getInt(COLUMN_COMMENTS_COUNT));

                carItemList.add(carItemDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carItemList;

    }

    @Override
    public CarInfoDTO getCarInfo(int carID) throws DAOException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        CarInfoDTO carInfoDTO = new CarInfoDTO();

        try {

            statement = connection.prepareStatement(DBQueries.FIND_CAR_BY_ID); //потом поменять запрос
            statement.setInt(1, carID);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Car car = ResultSetParser.createCarWithPhotos(resultSet);
//
//                String photoUrl = resultSet.getString("photo_url");
//                car.addPhoto(photoUrl);
//
//                while (resultSet.next()) {
//                    car.addPhoto(resultSet.getString("photo_url"));
//                }

                System.out.println("PHOTOS SIZE IS " + car.getPhotos().size());
                carInfoDTO.setCar(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carInfoDTO;
    }

    @Override
    public boolean addCar(AddCarDTO addCarDTO) throws DAOException {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.INSERT_CAR);
            statement.setString(1, addCarDTO.getBrand());
            statement.setString(2, addCarDTO.getModel());
            statement.setString(3, addCarDTO.getCarClass());
            statement.setString(4, addCarDTO.getColor());
            statement.setInt(5, addCarDTO.getYearOfIssue());
            statement.setInt(6, addCarDTO.getNumberOfSeats());
            statement.setDouble(7, addCarDTO.getEngineVolume());
            statement.setDouble(8, addCarDTO.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean editCar(Car car) throws DAOException {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.UPDATE_CAR);
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getCarClass());
            statement.setInt(4, car.getYearOfIssue());
            statement.setInt(5, car.getNumberOfSeats());
            statement.setString(6, car.getColor());
            statement.setDouble(7, car.getEngineVolume());
            statement.setBoolean(8, car.isDeleted());
            statement.setDouble(9, car.getPrice());
            statement.setInt(10, car.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true; //переделать

    }

    @Override
    public boolean deleteCar(int carID) throws DAOException {

        PreparedStatement statement = null;

        try {

            //может не делитом?
            statement = connection.prepareStatement("DELETE FROM car_list WHERE id_car = ?");
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

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
    public OrderInfoDTO getOrderInfo(int orderID) throws DAOException {

        OrderInfoDTO orderInfoDTO = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_ORDER_INFO_FOR_ADMIN);
            statement.setInt(1, orderID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                orderInfoDTO = ResultSetParser.createOrderInfoDTO(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderInfoDTO;

    }

    @Override
    public void confirmOrder(int orderID) throws DAOException {

        final int STATUS_CONFIRMED = 1;

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DBQueries.CONFIRM_ORDER);
            statement.setInt(1, STATUS_CONFIRMED);
            statement.setInt(2, orderID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void rejectOrder(int orderID) throws DAOException {

        final int STATUS_REJECTED = 2;

        try {

            PreparedStatement statement = connection.prepareStatement(DBQueries.REJECT_ORDER);
            statement.setInt(1, STATUS_REJECTED);
            statement.setInt(2, orderID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
