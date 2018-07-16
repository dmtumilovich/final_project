package by.epam.rentacar.dao;

import by.epam.rentacar.dao.impl.CarDAOImpl;
import by.epam.rentacar.dao.impl.ReviewDAOImpl;
import by.epam.rentacar.dao.impl.UserDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final CarDAO carDAO = new CarDAOImpl();
    private final ReviewDAO reviewDAO = new ReviewDAOImpl();

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

    }

}
