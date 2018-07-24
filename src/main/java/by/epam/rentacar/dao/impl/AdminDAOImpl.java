package by.epam.rentacar.dao.impl;

import by.epam.rentacar.dao.AdminDAO;
import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.dao.util.ResultSetParser;
import by.epam.rentacar.dao.util.constant.DBSchema;
import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.domain.dto.CarInfoDTO;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.domain.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl extends AdminDAO {

    @Override
    public List<CarItemDTO> getCarList() throws DAOException {

        List<CarItemDTO> carItemList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement("SELECT car_list.id_car, car_list.brand, car_list.model, car_list.class, car_list.price, car_list.is_available, COUNT(car_review.id_car) AS comments_count\n" +
                                                    "FROM car_list\n" +
                                                    "LEFT JOIN car_review ON car_list.id_car = car_review.id_car\n" +
                                                    "GROUP BY car_list.id_car");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarItemDTO carItemDTO = new CarItemDTO();
                carItemDTO.setCarID(resultSet.getInt(DBSchema.CarListTable.ID_CAR));
                carItemDTO.setBrand(resultSet.getString(DBSchema.CarListTable.BRAND));
                carItemDTO.setModel(resultSet.getString(DBSchema.CarListTable.MODEL));
                carItemDTO.setCarClass(resultSet.getString(DBSchema.CarListTable.CLASS));
                carItemDTO.setPrice(resultSet.getDouble(DBSchema.CarListTable.PRICE));
                carItemDTO.setStatus("Available");
                carItemDTO.setCommentsCount(resultSet.getInt("comments_count"));

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

            statement = connection.prepareStatement("SELECT * FROM car_list WHERE id_car = ?");
            statement.setInt(1, carID);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Car car = ResultSetParser.createCar(resultSet);
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

            statement = connection.prepareStatement("INSERT INTO car_list (brand, model, class, color, year_of_issue, number_of_seats, engine_volume, is_available, price) VALUES (?, ? ,?, ?, ?, ?, ?, '1', ?)");
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

            statement = connection.prepareStatement("UPDATE car_list\n" +
                                                    "SET brand = ?, model = ?, class = ?, year_of_issue = ?, number_of_seats = ?, color = ?, engine_volume = ?, is_available = ?, price = ?\n" +
                                                    "WHERE id_car = ?;");
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getCarClass());
            statement.setInt(4, car.getYearOfIssue());
            statement.setInt(5, car.getNumberOfSeats());
            statement.setString(6, car.getColor());
            statement.setDouble(7, car.getEngineVolume());
            statement.setBoolean(8, car.isAvailable());
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
}
