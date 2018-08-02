package by.epam.rentacar.domain.dto;

import by.epam.rentacar.domain.entity.Order;

public class OrderInfoDTO {

    private Order order;

    private int userID;
    private String username;
    private String name;
    private String surname;
    private String phone;

    private int carID;
    private String brand;
    private String model;
    private String carClass;
    private double price;

    public OrderInfoDTO() {

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO{" +
                "order=" + order +
                ", userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", carID='" + carID + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carClass='" + carClass + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
