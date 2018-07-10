package by.epam.rentacar.dao;

import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.entity.Car;
import by.epam.rentacar.util.ResultSetParser;
import by.epam.rentacar.util.constant.DBQueries;
import com.mysql.cj.api.mysqla.result.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_ALL_CARS);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Car car = ResultSetParser.createCar(rs);
                carList.add(car);
            }
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("carDAO: carList size is " + carList.size());
        return carList;
    }

    public Car getCarByID(int carID) {

        Car car = null;

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            connectionPool.initPoolData();
            Connection connection = connectionPool.takeConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT id_car, brand, model , class, year_of_issue, number_of_seats, color, engine_volume, is_available, price FROM car_list WHERE id_car = ?");
            statement.setInt(1, carID);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                car = ResultSetParser.createCar(rs);
            }
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

}
