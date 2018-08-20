package by.epam.rentacar.domain.dto;

import java.io.Serializable;

public class CarDTO implements Serializable {

    private static final long serialVersionUID = -4853985291334340602L;

    private String id;
    private String brand;
    private String model;
    private String carClass;
    private String color;
    private String yearOfIssue;
    private String numberOfSeats;
    private String engineVolume;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(String engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carClass='" + carClass + '\'' +
                ", color='" + color + '\'' +
                ", yearOfIssue='" + yearOfIssue + '\'' +
                ", numberOfSeats='" + numberOfSeats + '\'' +
                ", engineVolume='" + engineVolume + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
