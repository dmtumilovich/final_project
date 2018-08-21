package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.util.constant.DBSchema;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarDAOImpl extends CarDAO {

    private static final String COLUMN_CARS_COUNT = "cars_count";

    @Override
    public void add(Car car) throws DAOException {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(DBQueries.INSERT_CAR);
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getCarClass());
            statement.setString(4, car.getColor());
            statement.setInt(5, car.getYearOfIssue());
            statement.setInt(6, car.getNumberOfSeats());
            statement.setDouble(7, car.getEngineVolume());
            statement.setDouble(8, car.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while inserting car into database!", e);
        }

    }

    @Override
    public void update(Car car) throws DAOException {

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
            statement.setDouble(8, car.getPrice());
            statement.setInt(9, car.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while updating car!", e);
        }

    }

    @Override
    public void delete(int carID) throws DAOException {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DBQueries.DELETE_CAR);
            statement.setInt(1, carID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while setting deleted status as true", e);
        }

    }

    @Override
    public Car getByID(int carID) throws DAOException {

        Car car = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(DBQueries.FIND_CAR_BY_ID);
            statement.setInt(1, carID);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                car = ResultSetParser.createCarWithPhotos(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error while getting car info by id.", e);
        }

        return car;
    }

    @Override
    public List<Car> getAll() throws DAOException {
        throw new UnsupportedOperationException("Invalid operation for carDAO");
    }

    //сделать
    @Override
    public List<Car> getAll(int page, int itemsPerPage) throws DAOException {
        throw new UnsupportedOperationException("Invalid operation for carDAO");
    }

    @Override
    public List<Car> getAllNotDeleted(int page, int itemsPerPage) throws DAOException {

        List<Car> carList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.FIND_ALL_NOT_DELETED_CARS);
            statement.setInt(1, itemsPerPage);
            statement.setInt(2, (page - 1) * itemsPerPage);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = ResultSetParser.createCarWithPhotos(resultSet);
                carList.add(car);
            }

        } catch (SQLException e) {
            throw new DAOException("Error while getting cars!", e);
        }

        return carList;

    }

    @Override
    public List<Car> getAllNotDeletedByDateRange(Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException {

        List<Car> carList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.FIND_ALL_NOT_DELETED_CARS_BY_DATE_RANGE);
            Timestamp dateStartTS = new Timestamp(dateStart.getTime());
            Timestamp dateEndTS = new Timestamp(dateEnd.getTime());
            statement.setTimestamp(1, dateEndTS);
            statement.setTimestamp(2, dateStartTS);
            statement.setTimestamp(3, dateEndTS);
            statement.setInt(4, itemsPerPage);
            statement.setInt(5, calculateOffset(page, itemsPerPage));

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = ResultSetParser.createCarWithPhotos(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting cars by date range!", e);
        }

        return carList;

    }

    @Override
    public List<Car> getAllNotDeletedByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException {

        List<Car> carList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.FIND_ALL_NOT_DELETED_CARS_BY_DATE_RANGE_AND_CLASS);
            Timestamp dateStartTS = new Timestamp(dateStart.getTime());
            Timestamp dateEndTS = new Timestamp(dateEnd.getTime());
            statement.setString(1, carClass);
            statement.setTimestamp(2, dateEndTS);
            statement.setTimestamp(3, dateStartTS);
            statement.setTimestamp(4, dateEndTS);
            statement.setInt(5, itemsPerPage);
            statement.setInt(6, calculateOffset(page, itemsPerPage));

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = ResultSetParser.createCarWithPhotos(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting cars by date range and class!", e);
        }

        return carList;

    }

    //сделать
    @Override
    public int getTotalCount() throws DAOException {
        return 0;
    }

    @Override
    public int getTotalNotDeletedCount() throws DAOException {

        int carsCount = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_COUNT_OF_NOT_DELETED_CARS);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                carsCount = resultSet.getInt(COLUMN_CARS_COUNT);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carsCount;

    }

    @Override
    public int getTotalCountByDateRange(Date dateStart, Date dateEnd) throws DAOException {

        int carsCount = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_COUNT_OF_NOT_DELETED_CARS_BY_DATE_RANGE);
            Timestamp dateStartTS = new Timestamp(dateStart.getTime());
            Timestamp dateEndTS = new Timestamp(dateEnd.getTime());
            statement.setTimestamp(1, dateEndTS);
            statement.setTimestamp(2, dateStartTS);
            statement.setTimestamp(3, dateEndTS);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                carsCount = resultSet.getInt(COLUMN_CARS_COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while counting cars by date range", e);
        }

        return carsCount;

    }

    @Override
    public int getTotalCountByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd) throws DAOException {

        int carsCount = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_COUNT_OF_NOT_DELETED_CARS_BY_DATE_RANGE_AND_CLASS);
            Timestamp dateStartTS = new Timestamp(dateStart.getTime());
            Timestamp dateEndTS = new Timestamp(dateEnd.getTime());
            statement.setString(1, carClass);
            statement.setTimestamp(2, dateEndTS);
            statement.setTimestamp(3, dateStartTS);
            statement.setTimestamp(4, dateEndTS);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                carsCount = resultSet.getInt(COLUMN_CARS_COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while counting cars by date range", e);
        }

        return carsCount;

    }

    @Override
    public double getPriceByCarID(int carID) throws DAOException {

        double price = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(DBQueries.GET_CAR_PRICE_BY_ID);
            statement.setInt(1, carID);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getDouble(DBSchema.CarListTable.PRICE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("error while getting price by car id", e);
        }

        return price;

    }

    @Override
    public boolean isCarAvailable(int carID, Date dateStart, Date dateEnd) throws DAOException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT id_order\n" +
                    "FROM order_list\n" +
                    "WHERE id_car = ?\n" +
                    "AND ((date_end BETWEEN ? AND ?) OR (? BETWEEN date_start AND date_end))\n" +
                    "AND (id_status =  '1' OR id_status = '4')");
            Timestamp dateStartTS = new Timestamp(dateStart.getTime());
            Timestamp dateEndTS = new Timestamp(dateEnd.getTime());
            statement.setInt(1, carID);
            statement.setTimestamp(2, dateStartTS);
            statement.setTimestamp(3, dateEndTS);
            statement.setTimestamp(4, dateEndTS);

            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new DAOException("Error while checking is car available", e);
        }

        return false;

    }

    @Override
    public void addPhoto(int carID, String filename) throws DAOException {

        PreparedStatement statement = null;

        try {
        statement = connection.prepareStatement(DBQueries.ADD_CAR_PHOTO);
            statement.setInt(1, carID);
            statement.setString(2, filename);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("error while adding car photo", e);
        }

    }

    @Override
    public void deletePhoto(int photoID) throws DAOException {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DBQueries.DELETE_CAR_PHOTO);
            statement.setInt(1, photoID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while deleting car photo", e);
        }

    }
}
