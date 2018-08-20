package by.epam.rentacar.domain.dto;

import java.io.Serializable;

public class FindCarsDTO implements Serializable {

    private static final long serialVersionUID = -4467637074332697509L;

    private String carClass;
    private String dateStart;
    private String dateEnd;

    public FindCarsDTO() {

    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
