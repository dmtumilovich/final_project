package by.epam.rentacar.domain.dto;

import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.domain.entity.User;

public class OrderInfoDTO {

    private Order order;
    private User user;
    private Car car;

    public OrderInfoDTO() {

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO{" +
                "order=" + order +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
}
