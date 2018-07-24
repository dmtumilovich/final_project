package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.CarSearchDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Review;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    private ConnectionPool connectionPool;

    public CarDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Car> getAllCars() throws DAOException {

        List<Car> carList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

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

        Car car = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(DBQueries.FIND_CAR_WITH_REVIEWS_BY_ID);
            statement.setInt(1, carID);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                car = ResultSetParser.createCar(resultSet);
                Review review;
                if ((review = ResultSetParser.createReview(resultSet)) != null)  {
                    car.addReview(review);
                }
            }

            while (resultSet.next()) {
                car.addReview(ResultSetParser.createReview(resultSet));
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

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            System.out.println(createSearchQueryFromDTO(carSearchDTO));
            connection = connectionPool.takeConnection();

//            statement = connection.prepareStatement("SELECT * FROM car_list WHERE brand = ? and model = ? and color = ? and class = ? and" +
//                                                    " year_of_issue BETWEEN ? and ? and engine_volume BETWEEN ? and ? and price BETWEEN ? and ?");
//
//            statement.setString(1, carSearchDTO.getBrand());
//            statement.setString(2,carSearchDTO.getModel());
//            statement.setString(3,carSearchDTO.getColor());
//            statement.setString(4,carSearchDTO.getCarClass());
//            statement.setInt(5,carSearchDTO.getYearFrom());
//            statement.setInt(6,carSearchDTO.getYearTo());
//            statement.setDouble(7, carSearchDTO.getVolumeFrom());
//            statement.setDouble(8, carSearchDTO.getVolumeTo());
//            statement.setDouble(9,carSearchDTO.getPriceFrom());
//            statement.setDouble(10,carSearchDTO.getPriceTo());

            String searchQuery = createSearchQueryFromDTO(carSearchDTO);
            statement = connection.prepareStatement(searchQuery);

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
            //connectionPool.closeConnection(connection, statement, resultSet);
        }

        //System.out.println("size: " + carList.size());

        return carList;

    }

    private String createSearchQueryFromDTO(CarSearchDTO carSearchDTO) {

        final String AND = " and ";
        final String QUOTE = "\'";

        StringBuilder builder = new StringBuilder("SELECT * FROM car_list WHERE ");
        if (!carSearchDTO.getCarClass().isEmpty()) {
            builder.append("class = ")
                    .append(QUOTE).append(carSearchDTO.getCarClass()).append(QUOTE)
                    .append(AND);
        }
        if (!carSearchDTO.getBrand().isEmpty()) {
            builder.append("brand = ")
                    .append(QUOTE).append(carSearchDTO.getBrand()).append(QUOTE)
                    .append(AND);
        }
        if (!carSearchDTO.getModel().isEmpty()) {
            builder.append("model = ")
                    .append(QUOTE).append(carSearchDTO.getModel()).append(QUOTE)
                    .append(AND);
        }
        if (!carSearchDTO.getColor().isEmpty()) {
            builder.append("color = ")
                    .append(QUOTE).append(carSearchDTO.getColor()).append(QUOTE)
                    .append(AND);
        }
        if (carSearchDTO.getYearFrom() != 0 || carSearchDTO.getYearTo() != 0) {
            builder.append("year_of_issue BETWEEN ")
                    .append(carSearchDTO.getYearFrom())
                    .append(AND)
                    .append(carSearchDTO.getYearTo())
                    .append(AND);
        }
        if (carSearchDTO.getVolumeFrom() != 0 || carSearchDTO.getVolumeTo() != 0) {
            builder.append("engine_volume BETWEEN ")
                    .append(carSearchDTO.getVolumeFrom())
                    .append(AND)
                    .append(carSearchDTO.getVolumeTo())
                    .append(AND);
        }
        if (carSearchDTO.getPriceFrom() != 0 || carSearchDTO.getPriceTo() != 0) {
            builder.append("price BETWEEN ")
                    .append(carSearchDTO.getPriceFrom())
                    .append(AND)
                    .append(carSearchDTO.getPriceTo())
                    .append(AND);
        }

        builder.delete(builder.length() - AND.length(), builder.length());

        return builder.toString();
    }

}
