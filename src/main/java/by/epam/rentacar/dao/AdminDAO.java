package by.epam.rentacar.dao;

import by.epam.rentacar.dao.exception.DAOException;
import by.epam.rentacar.domain.dto.AddCarDTO;
import by.epam.rentacar.domain.dto.CarInfoDTO;
import by.epam.rentacar.domain.dto.CarItemDTO;
import by.epam.rentacar.domain.entity.Car;

import java.util.List;

public interface AdminDAO {

    List<CarItemDTO> getCarList() throws DAOException;
    CarInfoDTO getCarInfo(int carID) throws DAOException;
    boolean addCar(AddCarDTO addCarDTO) throws DAOException;
    boolean editCar(Car car) throws DAOException;
    boolean deleteCar(int carID) throws DAOException;

}
