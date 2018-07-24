package by.epam.rentacar.domain.dto;

public class AddCarDTO {

    private String brand;
    private String model;
    private String carClass;
    private int yearOfIssue;
    private int numberOfSeats;
    private String color;
    private double engineVolume;
    private double price;

    public AddCarDTO() {

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

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AddCarDTO{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carClass='" + carClass + '\'' +
                ", yearOfIssue=" + yearOfIssue +
                ", numberOfSeats=" + numberOfSeats +
                ", color='" + color + '\'' +
                ", engineVolume=" + engineVolume +
                ", price=" + price +
                '}';
    }
}
