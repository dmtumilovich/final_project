package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.CarSearchDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandFindCars implements Command {

    private static final Logger logger = LogManager.getLogger(CommandFindCars.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        CarSearchDTO carSearchDTO = createCarSearchDTO(request);
        System.out.println(carSearchDTO);
        List<Car> carList = null;

        try {
            carList = ServiceFactory.getInstance().getCarService().getCarsByFilter(carSearchDTO);

            request.setAttribute(RequestAttributes.KEY_CAR_LIST, carList);
            request.getRequestDispatcher(PageParameters.PAGE_CARS).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to find cars by parameters!", e);
        }

    }

    private CarSearchDTO createCarSearchDTO(HttpServletRequest request) {
        String carClass = request.getParameter(RequestParameters.KEY_CAR_CLASS);
        carClass = (carClass == null) ? "" : carClass;
        String brand = request.getParameter(RequestParameters.KEY_CAR_BRAND);
        brand = (brand == null) ? "" : brand;
        String model = request.getParameter(RequestParameters.KEY_CAR_MODEL);
        model = (model == null) ? "" : model;
        String color = request.getParameter(RequestParameters.KEY_CAR_COLOR);
        color = (color == null) ? "" : color;

        String yearFromStr = request.getParameter(RequestParameters.KEY_YEAR_FROM);
        int yearFrom = (yearFromStr == null || yearFromStr.isEmpty()) ? 0 : Integer.parseInt(yearFromStr);
        String yearToStr = request.getParameter(RequestParameters.KEY_YEAR_TO);
        int yearTo = (yearToStr == null || yearToStr.isEmpty()) ? 0 : Integer.parseInt(yearToStr);

        String volumeFromStr = request.getParameter(RequestParameters.KEY_VOLUME_FROM);
        double volumeFrom = (volumeFromStr == null || volumeFromStr.isEmpty()) ? 0 : Double.parseDouble(volumeFromStr);
        String volumeToStr = request.getParameter(RequestParameters.KEY_VOLUME_TO);
        double volumeTo = (volumeToStr == null || volumeToStr.isEmpty()) ? 0 : Double.parseDouble(volumeToStr);
        String priceFromStr = request.getParameter(RequestParameters.KEY_PRICE_FROM);
        double priceFrom = (priceFromStr == null || priceFromStr.isEmpty()) ? 0 : Double.parseDouble(priceFromStr);
        String priceToStr = request.getParameter(RequestParameters.KEY_PRICE_TO);
        double priceTo = (priceToStr == null || priceToStr.isEmpty()) ? 0 : Double.parseDouble(priceToStr);

        CarSearchDTO.Builder builder = new CarSearchDTO.Builder();
        builder.setBrand(brand).setModel(model).setCarClass(carClass)
                .setColor(color).setYearFrom(yearFrom).setYearTo(yearTo)
                .setVolumeFrom(volumeFrom).setVolumeTo(volumeTo)
                .setPriceFrom(priceFrom).setPriceTo(priceTo);

        return builder.build();
    }
}
