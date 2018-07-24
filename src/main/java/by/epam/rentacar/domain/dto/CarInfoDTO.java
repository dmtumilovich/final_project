package by.epam.rentacar.domain.dto;

import by.epam.rentacar.domain.entity.Car;

public class CarInfoDTO {

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
