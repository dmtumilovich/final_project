package by.epam.rentacar.domain.entity;

public class CarPhoto {

    int id;
    int carID;
    String url;

    public CarPhoto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String photoUrl) {
        this.url = photoUrl;
    }
}
