package by.epam.rentacar.dao;

import by.epam.rentacar.dao.connection.pool.ConnectionPool;
import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.dao.impl.ReviewDAOImpl;
import by.epam.rentacar.dao.impl.UserDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserDAO userDAO;
    private CarDAO carDAO;
    private ReviewDAO reviewDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {

        userDAO = new UserDAOImpl();
        carDAO = new CarDAOImpl();
        reviewDAO = new ReviewDAOImpl();

    }

}
