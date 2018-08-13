package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.CarDAO;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.util.constant.DBSchema;
import by.epam.rentacar.domain.dto.CarSearchDTO;
import by.epam.rentacar.domain.dto.FindCarsDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBQueries;
import com.mysql.cj.api.mysqla.result.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarDAOImpl extends CarDAO {

    public List<Car> getAllCars() throws DAOException {

        List<Car> carList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(DBQueries.FIND_ALL_CARS);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Car car = ResultSetParser.createCarWithPhotos(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting car list.", e);
        }

        return carList;
    }

    @Override
    public List<Car> getAllNotDeleted(int page, int itemsPerPage) throws DAOException {

        List<Car> carList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_deleted, price, photos.id_photo, photos.photo_url\n" +
                                                        "FROM car_list\n" +
                                                        "LEFT JOIN (SELECT id_photo, id_car, photo_url\n" +
                                                        "           FROM car_photos\n" +
                                                        "           WHERE id_photo IN (SELECT MIN(id_photo)\n" +
                                                        "                               FROM car_photos\n" +
                                                        "                               GROUP BY id_car)\n" +
                                                        "                              ) photos\n" +
                                                        "ON car_list.id_car = photos.id_car\n" +
                                                        "WHERE is_deleted = '0'\n" +
                                                        "GROUP BY car_list.id_car, photos.id_photo LIMIT ? OFFSET ?");
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
    public List<Car> getAllByDateRange(Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException {

        List<Car> carList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_deleted, price, photos.id_photo, photos.photo_url\n" +
                                                        "FROM car_list\n" +
                                                        "LEFT JOIN (SELECT id_photo, id_car, photo_url\n" +
                                                        "FROM car_photos\n" +
                                                        "WHERE id_photo IN (SELECT MIN(id_photo)\n" +
                                                        "FROM car_photos\n" +
                                                        "GROUP BY id_car)\n" +
                                                        ") photos\n" +
                                                        "ON car_list.id_car = photos.id_car\n" +
                                                        "WHERE is_deleted = '0' AND car_list.id_car NOT IN (SELECT DISTINCT order_list.id_car \n" +
                                                        "FROM order_list\n" +
                                                        "WHERE ((? BETWEEN date_start AND date_end) \n" +
                                                        "OR (? BETWEEN date_start AND date_end)) AND (id_status = '1' or id_status = '3' or id_status = '4')\n" +
                                                        ")\n" +
                                                        "GROUP BY car_list.id_car, photos.id_photo LIMIT ? OFFSET ?");
            statement.setTimestamp(1, new Timestamp(dateStart.getTime()));
            statement.setTimestamp(2, new Timestamp(dateEnd.getTime()));
            statement.setInt(3, itemsPerPage);
            statement.setInt(4, calculateOffset(page, itemsPerPage));

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
    public List<Car> getAllByDateRangeAndClass(String carClass, Date dateStart, Date dateEnd, int page, int itemsPerPage) throws DAOException {

        List<Car> carList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT car_list.id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_deleted, price, photos.id_photo, photos.photo_url\n" +
                                                        "FROM car_list\n" +
                                                        "LEFT JOIN (SELECT id_photo, id_car, photo_url\n" +
                                                        "FROM car_photos\n" +
                                                        "WHERE id_photo IN (SELECT MIN(id_photo)\n" +
                                                        "FROM car_photos\n" +
                                                        "GROUP BY id_car)\n" +
                                                        ") photos\n" +
                                                        "ON car_list.id_car = photos.id_car\n" +
                                                        "WHERE is_deleted = '0' AND class = ? AND car_list.id_car NOT IN (SELECT DISTINCT order_list.id_car \n" +
                                                        "FROM order_list\n" +
                                                        "WHERE ((? BETWEEN date_start AND date_end) \n" +
                                                        "OR (? BETWEEN date_start AND date_end)) AND (id_status = '1' or id_status = '3' or id_status = '4')\n" +
                                                        ")\n" +
                                                        "GROUP BY car_list.id_car, photos.id_photo LIMIT ? OFFSET ?");
            statement.setString(1, carClass);
            statement.setTimestamp(2, new Timestamp(dateStart.getTime()));
            statement.setTimestamp(3, new Timestamp(dateEnd.getTime()));
            statement.setInt(4, itemsPerPage);
            statement.setInt(5, calculateOffset(page, itemsPerPage));

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

    public Car getCarByID(int carID) throws DAOException {

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
    public void deleteCar(int carID) throws DAOException {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("UPDATE car_list\n" +
                                                    "SET is_deleted = '1'\n" +
                                                    "WHERE id_car = ?");
            statement.setInt(1, carID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while setting car_list.is_deleted to 1", e); //?????
        }

    }

    @Override
    public List<Car> getCarsByFilter(CarSearchDTO carSearchDTO) throws DAOException {

        List<Car> carList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

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
            System.out.println("QUERY:");
            System.out.println(searchQuery);
            statement = connection.prepareStatement(searchQuery);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car car = ResultSetParser.createCarWithPhotos(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting car list by filter.", e);
        }

        return carList;

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
    public void addPhoto(int carID, String filename) throws DAOException {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO car_photos (id_car, photo_url)\n" +
                                                    "VALUES (?, ?)");
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
            statement = connection.prepareStatement("DELETE FROM car_photos WHERE id_photo = ?");
            statement.setInt(1, photoID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while deleting car photo", e);
        }

    }

    @Override
    public int getTotalCount() throws DAOException {

        int carsCount = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT COUNT(id_car) AS total_count\n" +
                                                        "FROM car_list\n" +
                                                        "WHERE is_deleted != '1'");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                carsCount = resultSet.getInt("total_count");
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
            statement = connection.prepareStatement("SELECT COUNT(car_list.id_car) AS cars_count\n" +
                                                        "FROM car_list\n" +
                                                        "WHERE is_deleted = '0' AND car_list.id_car NOT IN (SELECT DISTINCT order_list.id_car \n" +
                                                        "FROM order_list\n" +
                                                        "WHERE ((? BETWEEN date_start AND date_end) \n" +
                                                        "OR (? BETWEEN date_start AND date_end)) AND (id_status = '1' or id_status = '3' or id_status = '4')\n" +
                                                        ")");
            statement.setTimestamp(1, new Timestamp(dateStart.getTime()));
            statement.setTimestamp(2, new Timestamp(dateEnd.getTime()));

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                carsCount = resultSet.getInt("cars_count");
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
            statement = connection.prepareStatement("SELECT COUNT(car_list.id_car) AS cars_count\n" +
                                                        "FROM car_list\n" +
                                                        "WHERE is_deleted = '0' AND class = ? AND car_list.id_car NOT IN (SELECT DISTINCT order_list.id_car \n" +
                                                        "FROM order_list\n" +
                                                        "WHERE ((? BETWEEN date_start AND date_end)\n" +
                                                        "OR (? BETWEEN date_start AND date_end))\n" +
                                                        "AND (id_status = '1' or id_status = '3' or id_status = '4')\n" +
                                                        ");");
            statement.setString(1, carClass);
            statement.setTimestamp(2, new Timestamp(dateStart.getTime()));
            statement.setTimestamp(3, new Timestamp(dateEnd.getTime()));

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                carsCount = resultSet.getInt("cars_count");
            }
        } catch (SQLException e) {
            throw new DAOException("Error while counting cars by date range", e);
        }

        return carsCount;

    }

    private String createSearchQueryFromDTO(CarSearchDTO carSearchDTO) {

        final String AND = " and ";
        final String QUOTE = "\'";

        StringBuilder builder = new StringBuilder("SELECT car_list.*, car_photos.id_photo, car_photos.photo_url\n" +
                                                    "FROM car_list\n" +
                                                    "LEFT JOIN car_photos\n" +
                                                    "ON car_list.id_car = car_photos.id_car\n" +
                                                    "WHERE ");
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
