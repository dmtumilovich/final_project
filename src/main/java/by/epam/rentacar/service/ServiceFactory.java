package by.epam.rentacar.service;

import by.epam.rentacar.service.impl.*;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final CarService carService = new CarServiceImpl();
    private final ReviewService reviewService = new ReviewServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public CarService getCarService() {
        return carService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {

    }

}
