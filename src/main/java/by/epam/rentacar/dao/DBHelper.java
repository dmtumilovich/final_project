package by.epam.rentacar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static Connection connection;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/rent_a_car?autoReconnect=true&useSSL=false";
    private static final String UNAME = "root";
    private static final String PASSWORD = "root";

    private DBHelper() {

    }

    static {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, UNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
