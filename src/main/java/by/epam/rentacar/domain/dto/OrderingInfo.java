package by.epam.rentacar.domain.dto;

import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.domain.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderingInfo implements Serializable {

    private static final long serialVersionUID = 6030192088597367050L;

    private User user;
    private Car car;
    private int numberOfDays;
    private double totalCost;

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

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "OrderingInfo{" +
                "user=" + user +
                ", car=" + car +
                ", isAllUserData=" + isAllUserData +
                '}';
    }

    public static class DateRange {

        private Date startDate;
        private Date endDate;

        public DateRange() {

        }

        public DateRange(Date startDate, Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }
}
