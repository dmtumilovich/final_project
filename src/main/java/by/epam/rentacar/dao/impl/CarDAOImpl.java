package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dto.CarSearchDTO;
import by.epam.rentacar.entity.Car;
import by.epam.rentacar.util.ResultSetParser;
import by.epam.rentacar.util.constant.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    public List<Car> getAllCars() throws DAOException {
        List<Car> carList = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(DBQueries.FIND_ALL_CARS);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Car car = ResultSetParser.createCar(resultSet);
                carList.add(car);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection error!", e);
        } catch (SQLException e) {
            throw new DAOException("Error while getting car list.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return carList;
    }

    public Car getCarByID(int carID) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Car car = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(DBQueries.FIND_CAR_BY_ID);
            statement.setInt(1, carID);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                car = ResultSetParser.createCar(resultSet);
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection error!", e);
        } catch (SQLException e) {
            throw new DAOException("Error while getting car info by id.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return car;
    }

    @Override
    public List<Car> getCarsByFilter(CarSearchDTO carSearchDTO) throws DAOException {
        List<Car> carList = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement("SELECT * FROM car_list WHERE brand = ? and model = ? and color = ? and class = ? and" +
                                                    " year_of_issue BETWEEN ? and ? and engine_volume BETWEEN ? and ? and price BETWEEN ? and ?");

            statement.setString(1, carSearchDTO.getBrand());
            statement.setString(2,carSearchDTO.getModel());
            statement.setString(3,carSearchDTO.getColor());
            statement.setString(4,carSearchDTO.getCarClass());
            statement.setInt(5,carSearchDTO.getYearFrom());
            statement.setInt(6,carSearchDTO.getYearTo());
            statement.setDouble(7, carSearchDTO.getVolumeFrom());
            statement.setDouble(8, carSearchDTO.getVolumeTo());
            statement.setDouble(9,carSearchDTO.getPriceFrom());
            statement.setDouble(10,carSearchDTO.getPriceTo());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car car = ResultSetParser.createCar(resultSet);
                carList.add(car);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection error!", e);
        } catch (SQLException e) {
            throw new DAOException("Error while getting car list by filter.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        System.out.println("size: " + carList.size());

        return carList;

    }

}
