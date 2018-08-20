package by.epam.rentacar.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class Order extends Entity {

    private static final long serialVersionUID = 7337015533621579916L;

    private int id;
    private int userID;
    private int carID;
    private Date dateStart;
    private Date dateEnd;
    private double totalPrice;
    private Status status;

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        CONFIRMED,
        REJECTED,
        AWAITS,
        RENT,
        OVER,
        RETURNED,
        CANCELED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userID != order.userID) return false;
        if (carID != order.carID) return false;
        if (Double.compare(order.totalPrice, totalPrice) != 0) return false;
        if (dateStart != null ? !dateStart.equals(order.dateStart) : order.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(order.dateEnd) : order.dateEnd != null) return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + userID;
        result = 31 * result + carID;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", carID=" + carID +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
