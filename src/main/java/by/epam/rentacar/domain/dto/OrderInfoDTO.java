package by.epam.rentacar.domain.dto;

import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;
import by.epam.rentacar.domain.entity.User;

import java.io.Serializable;

public class OrderInfoDTO implements Serializable {

    private static final long serialVersionUID = -1150104540536773084L;

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
