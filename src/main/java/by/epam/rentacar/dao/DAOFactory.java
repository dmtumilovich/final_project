package by.epam.rentacar.dao;

import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.connection.pool.ConnectionPoolException;
import by.epam.rentacar.dao.impl.AdminDAOImpl;
import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.dao.impl.ReviewDAOImpl;
import by.epam.rentacar.dao.impl.UserDAOImpl;

import java.sql.Connection;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private static ConnectionPool connectionPool = null;

    private UserDAO userDAO;
    private CarDAO carDAO;
    private ReviewDAO reviewDAO;
    private AdminDAO adminDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
        connectionPool = ConnectionPool.getInstance();

        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        userDAO = new UserDAOImpl(connectionPool);
        carDAO = new CarDAOImpl(connectionPool);
        reviewDAO = new ReviewDAOImpl(connectionPool);
        adminDAO = new AdminDAOImpl(connectionPool);

    }

}
