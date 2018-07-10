package by.epam.rentacar.service;

import by.epam.rentacar.service.impl.CarServiceImpl;
import by.epam.rentacar.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final CarService carService = new CarServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public CarService getCarService() {
        return carService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {

    }

}
