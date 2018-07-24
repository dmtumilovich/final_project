package by.epam.rentacar.controller.command;

import by.epam.rentacar.controller.util.constant.RequestParameters;
import by.epam.rentacar.domain.dto.CarSearchDTO;
import by.epam.rentacar.domain.entity.Car;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandFindCars implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String carClass = request.getParameter("class");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String yearFrom = request.getParameter("year_from");
        String yearTo = request.getParameter("year_to");
        String volumeFrom = request.getParameter("volume_from");
        String volumeTo = request.getParameter("volume_to");
        String priceFrom = request.getParameter("price_from");
        String priceTo = request.getParameter("price_to");

        CarSearchDTO carSearchDTO = createCarSearchDTO(request);
        System.out.println(carSearchDTO);
        List<Car> carList = new ArrayList<>();

        try {
            carList = ServiceFactory.getInstance().getCarService().getCarsByFilter(carSearchDTO);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(RequestAttributes.KEY_CAR_LIST, carList);
        request.getRequestDispatcher(PageParameters.PAGE_CARS).forward(request, response);

    }

    private CarSearchDTO createCarSearchDTO(HttpServletRequest request) {
        String carClass = request.getParameter(RequestParameters.KEY_CAR_CLASS);
        String brand = request.getParameter(RequestParameters.KEY_CAR_BRAND);
        String model = request.getParameter(RequestParameters.KEY_CAR_MODEL);
        String color = request.getParameter(RequestParameters.KEY_CAR_COLOR);

        String yearFromStr = request.getParameter(RequestParameters.KEY_YEAR_FROM);
        int yearFrom = yearFromStr.isEmpty() ? 0 : Integer.parseInt(yearFromStr);
        String yearToStr = request.getParameter(RequestParameters.KEY_YEAR_TO);
        int yearTo = yearToStr.isEmpty() ? 0 : Integer.parseInt(yearToStr);

        String volumeFromStr = request.getParameter(RequestParameters.KEY_VOLUME_FROM);
        double volumeFrom = volumeFromStr.isEmpty() ? 0 : Double.parseDouble(volumeFromStr);
        String volumeToStr = request.getParameter(RequestParameters.KEY_VOLUME_TO);
        double volumeTo = volumeToStr.isEmpty() ? 0 : Double.parseDouble(volumeToStr);
        String priceFromStr = request.getParameter(RequestParameters.KEY_PRICE_FROM);
        double priceFrom = priceFromStr.isEmpty() ? 0 : Double.parseDouble(priceFromStr);
        String priceToStr = request.getParameter(RequestParameters.KEY_PRICE_TO);
        double priceTo = priceToStr.isEmpty() ? 0 : Double.parseDouble(priceToStr);

        CarSearchDTO.Builder builder = new CarSearchDTO.Builder();
        builder.setBrand(brand).setModel(model).setCarClass(carClass)
                .setColor(color).setYearFrom(yearFrom).setYearTo(yearTo)
                .setVolumeFrom(volumeFrom).setVolumeTo(volumeTo)
                .setPriceFrom(priceFrom).setPriceTo(priceTo);

        return builder.build();
    }
}
