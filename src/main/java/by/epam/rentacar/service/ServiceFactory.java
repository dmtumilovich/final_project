package by.epam.rentacar.service;

import by.epam.rentacar.service.impl.AdminServiceImpl;
import by.epam.rentacar.service.impl.CarServiceImpl;
import by.epam.rentacar.service.impl.ReviewServiceImpl;
import by.epam.rentacar.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final CarService carService = new CarServiceImpl();
    private final ReviewService reviewService = new ReviewServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public CarService getCarService() {
        return carService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {

    }

}
