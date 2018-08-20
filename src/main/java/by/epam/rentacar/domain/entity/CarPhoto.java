package by.epam.rentacar.domain.entity;


public class CarPhoto extends Entity {

    private static final long serialVersionUID = 4851735165162162517L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarPhoto)) return false;

        CarPhoto carPhoto = (CarPhoto) o;

        if (id != carPhoto.id) return false;
        if (carID != carPhoto.carID) return false;
        return url != null ? url.equals(carPhoto.url) : carPhoto.url == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + carID;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarPhoto{" +
                "id=" + id +
                ", carID=" + carID +
                ", url='" + url + '\'' +
                '}';
    }
}
