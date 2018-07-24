package by.epam.rentacar.domain.dto;

public class CarSearchDTO {

    private String brand;
    private String model;
    private String carClass;
    private String color;
    private int yearFrom;
    private int yearTo;
    private double volumeFrom;
    private double volumeTo;
    private double priceFrom;
    private double priceTo;

    public CarSearchDTO() {

    }

    public CarSearchDTO(String brand, String model, String carClass, String color, int yearFrom, int yearTo, double volumeFrom, double volumeTo, double priceFrom, double priceTo) {
        this.brand = brand;
        this.model = model;
        this.carClass = carClass;
        this.color = color;
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
        this.volumeFrom = volumeFrom;
        this.volumeTo = volumeTo;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
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

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public double getVolumeFrom() {
        return volumeFrom;
    }

    public void setVolumeFrom(double volumeFrom) {
        this.volumeFrom = volumeFrom;
    }

    public double getVolumeTo() {
        return volumeTo;
    }

    public void setVolumeTo(double volumeTo) {
        this.volumeTo = volumeTo;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }


    @Override
    public String toString() {
        return "CarSearchDTO{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carClass='" + carClass + '\'' +
                ", color='" + color + '\'' +
                ", yearFrom=" + yearFrom +
                ", yearTo=" + yearTo +
                ", volumeFrom=" + volumeFrom +
                ", volumeTo=" + volumeTo +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                '}';
    }

    public static class Builder {
        private String brand;
        private String model;
        private String carClass;
        private String color;
        private int yearFrom;
        private int yearTo;
        private double volumeFrom;
        private double volumeTo;
        private double priceFrom;
        private double priceTo;

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setCarClass(String carClass) {
            this.carClass = carClass;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setYearFrom(int yearFrom) {
            this.yearFrom = yearFrom;
            return this;
        }

        public Builder setYearTo(int yearTo) {
            this.yearTo = yearTo;
            return this;
        }

        public Builder setVolumeFrom(double volumeFrom) {
            this.volumeFrom = volumeFrom;
            return this;
        }

        public Builder setVolumeTo(double volumeTo) {
            this.volumeTo = volumeTo;
            return this;
        }

        public Builder setPriceFrom(double priceFrom) {
            this.priceFrom = priceFrom;
            return this;
        }

        public Builder setPriceTo(double priceTo) {
            this.priceTo = priceTo;
            return this;
        }

        public CarSearchDTO build() {
            return new CarSearchDTO(brand, model, carClass, color, yearFrom, yearTo, volumeFrom, volumeTo, priceFrom, priceTo);
        }
    }
}
