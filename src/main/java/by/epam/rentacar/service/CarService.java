package by.epam.rentacar.service;

import by.epam.rentacar.domain.dto.CarDTO;
import by.epam.rentacar.domain.dto.FindCarsDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * The interface defines methods to work with car data.
 */
public interface CarService {

    /**
     * Gets cars according to the page and items per one page.
     *
     * @param page is the current page.
     * @param itemsPerPage is the number of items per one page.
     * @return list of {@link Car} objects.
     * @throws ServiceException
     */
    List<Car> getAllCars(int page, int itemsPerPage) throws ServiceException;

    /**
     * Gets all not deleted cars according to the page and items per one page.
     *
     * @param page is the current page.
     * @param itemsPerPage is the number of items per one page.
     * @return list of {@link Car} objects.
     * @throws ServiceException
     */
    List<Car> getAllNotDeletedCars(int page, int itemsPerPage) throws ServiceException;

    /**
     * Gets number of pages of cars.
     *
     * @param itemsPerPage is the number of items per one page.
     * @return the number of calculated pages.
     * @throws ServiceException
     */
    int getCarsPagesCount(int itemsPerPage) throws ServiceException;


    /**
     * Gets not deleted cars according to the selected date range, car class, page and items per one page.
     *
     * @param findCarsDTO is an {@link FindCarsDTO} objects, containing date range and car class.
     * @param page is the current page.
     * @param itemsPerPage is the number of items per one page.
     * @return list of {@link Car} objects.
     * @throws ServiceException
     */
    List<Car> getCarsByDateRangeAndClass(FindCarsDTO findCarsDTO, int page, int itemsPerPage) throws ServiceException;

    /**
     * Gets number of pages of cars according to the date range and car class.
     *
     * @param itemsPerPage is the number of items per one page.
     * @return the number of calculated pages.
     * @throws ServiceException
     */
    int getCarsPagesCount(FindCarsDTO findCarsDTO, int itemsPerPage) throws ServiceException;


    /**
     * Gets car data.
     *
     * @param carID is the id of the car.
     * @return an {@link Car} object.
     * @throws ServiceException
     */
    Car getCar(int carID) throws ServiceException;

    /**
     * Adds car if input data is valid.
     *
     * @param carDTO is an {@link CarDTO} object, containing input data.
     * @throws ServiceException
     */
    void addCar(CarDTO carDTO) throws ServiceException;

    /**
     * Changes car data if input data is valid.
     *
     * @param carDTO is an {@link CarDTO} object, containing input data.
     * @throws ServiceException
     */
    void editCar(CarDTO carDTO) throws ServiceException;

    /**
     * Deletes car with {@code carID}.
     *
     * @param carID is the id of the car.
     * @throws ServiceException
     */
    void deleteCar(int carID) throws ServiceException;

    /**
     * Adds photo to the car.
     *
     * @param carID is the id of the car.
     * @param filename is the photo's url.
     * @throws ServiceException
     */
    void addPhoto(int carID, String filename) throws ServiceException;

    /**
     * Deletes photo with {@code photoID}.
     *
     * @param photoID is the id of the car's photo.
     * @throws ServiceException
     */
    void deletePhoto(int photoID) throws ServiceException;

    /**
     * Checks if car available to rent or not.
     *
     * @param carID is the id of the car.
     * @param dateStart is the beginning of date range.
     * @param dateEnd is the end of date range.
     * @return {@code true} if car is available to rent or {@code false} if not.
     */
    boolean isAvailableToRent(int carID, String dateStart, String dateEnd) throws ServiceException;

}
