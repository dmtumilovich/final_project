package by.epam.rentacar.dao;

import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.dao.impl.OrderDAOImpl;
import by.epam.rentacar.dao.impl.ReviewDAOImpl;
import by.epam.rentacar.dao.impl.UserDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private static final UserDAO userDAO = new UserDAOImpl();
    private static final CarDAO carDAO = new CarDAOImpl();
    private static final ReviewDAO reviewDAO = new ReviewDAOImpl();
    private static final OrderDAO orderDAO = new OrderDAOImpl();

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {

    }

}
