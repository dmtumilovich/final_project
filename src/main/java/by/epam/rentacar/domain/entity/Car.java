package by.epam.rentacar.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Car extends Entity {

    private static final long serialVersionUID = 584464800269885522L;

    private int id;
    private String brand;
    private String model;
    private String carClass;
    private int yearOfIssue;
    private int numberOfSeats;
    private String color;
    private double engineVolume;
    private boolean isDeleted;
    private double price;

    private List<Review> reviewList = new ArrayList<>();
    private List<CarPhoto> photos = new ArrayList<>();

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public void addReview(Review review) {
        reviewList.add(review);
    }

    public int getNumberOfReviews() {
        return reviewList.size();
    }

    public List<CarPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<CarPhoto> photos) {
        this.photos = photos;
    }

    public void addPhoto(CarPhoto photo) {
        this.photos.add(photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (yearOfIssue != car.yearOfIssue) return false;
        if (numberOfSeats != car.numberOfSeats) return false;
        if (Double.compare(car.engineVolume, engineVolume) != 0) return false;
        if (isDeleted != car.isDeleted) return false;
        if (Double.compare(car.price, price) != 0) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (carClass != null ? !carClass.equals(car.carClass) : car.carClass != null) return false;
        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        if (reviewList != null ? !reviewList.equals(car.reviewList) : car.reviewList != null) return false;
        return photos != null ? photos.equals(car.photos) : car.photos == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        result = 31 * result + yearOfIssue;
        result = 31 * result + numberOfSeats;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        temp = Double.doubleToLongBits(engineVolume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isDeleted ? 1 : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (reviewList != null ? reviewList.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carClass='" + carClass + '\'' +
                ", yearOfIssue=" + yearOfIssue +
                ", numberOfSeats=" + numberOfSeats +
                ", color='" + color + '\'' +
                ", engineVolume=" + engineVolume +
                ", isDeleted=" + isDeleted +
                ", price=" + price +
                ", reviewList=" + reviewList +
                ", photos=" + photos +
                '}';
    }
}
