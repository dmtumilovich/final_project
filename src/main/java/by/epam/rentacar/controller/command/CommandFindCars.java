package by.epam.rentacar.controller.command;

import by.epam.rentacar.dto.CarSearchDTO;
import by.epam.rentacar.entity.Car;
import by.epam.rentacar.service.ServiceFactory;
import by.epam.rentacar.service.exception.ServiceException;
import by.epam.rentacar.util.constant.PageParameters;
import by.epam.rentacar.util.constant.RequestAttributes;

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
        //???????????????????????????????????????????????????????????????????????????????????????????????7
        String carClass = request.getParameter("class");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");

        String yearFromStr = request.getParameter("year_from");
        int yearFrom = yearFromStr.isEmpty() ? -1 : Integer.parseInt(yearFromStr);
        String yearToStr = request.getParameter("year_to");
        int yearTo = yearToStr.isEmpty() ? -1 : Integer.parseInt(yearToStr);

        String volumeFromStr = request.getParameter("volume_from");
        double volumeFrom = volumeFromStr.isEmpty() ? -1 : Double.parseDouble(volumeFromStr);
        String volumeToStr = request.getParameter("volume_to");
        double volumeTo = volumeToStr.isEmpty() ? -1 : Double.parseDouble(volumeToStr);
        String priceFromStr = request.getParameter("price_from");
        double priceFrom = priceFromStr.isEmpty() ? -1 : Double.parseDouble(priceFromStr);
        String priceToStr = request.getParameter("price_to");
        double priceTo = priceToStr.isEmpty() ? -1 : Double.parseDouble(priceToStr);

        CarSearchDTO.Builder builder = new CarSearchDTO.Builder();
        builder.setBrand(brand).setModel(model).setCarClass(carClass)
                .setColor(color).setYearFrom(yearFrom).setYearTo(yearTo)
                .setVolumeFrom(volumeFrom).setVolumeTo(volumeTo)
                .setPriceFrom(priceFrom).setPriceTo(priceTo);

        return builder.build();
    }
}
