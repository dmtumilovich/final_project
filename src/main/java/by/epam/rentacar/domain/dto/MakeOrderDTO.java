package by.epam.rentacar.domain.dto;

import java.io.Serializable;

public class MakeOrderDTO implements Serializable {

    private static final long serialVersionUID = 5590873329310882349L;

    private int userID;
    private int carID;
    private String dateStart;
    private String dateEnd;

    public MakeOrderDTO() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
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

    @Override
    public String toString() {
        return "MakeOrderDTO{" +
                "userID=" + userID +
                ", carID=" + carID +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
