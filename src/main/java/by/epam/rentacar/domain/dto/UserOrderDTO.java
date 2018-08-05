package by.epam.rentacar.domain.dto;

import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.Order;

public class UserOrderDTO {

    private Order order;
    private Car car;

    public UserOrderDTO() {

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "UserOrderDTO{" +
                "order=" + order +
                ", car=" + car +
                '}';
    }
}
