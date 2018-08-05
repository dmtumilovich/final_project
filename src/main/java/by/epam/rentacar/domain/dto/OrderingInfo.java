package by.epam.rentacar.domain.dto;

import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.User;

public class OrderingInfo {

    private User user;
    private Car car;
    private boolean isAllUserData = true;

    public OrderingInfo() {

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

    public boolean isAllUserData() {
        return isAllUserData;
    }

    public void setAllUserData(boolean allUserData) {
        isAllUserData = allUserData;
    }

    @Override
    public String toString() {
        return "OrderingInfo{" +
                "user=" + user +
                ", car=" + car +
                ", isAllUserData=" + isAllUserData +
                '}';
    }
}
