package by.epam.rentacar.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class Review extends Entity {

    private static final long serialVersionUID = 5190330198817508128L;

    private int id;
    private int userID;
    private int carID;
    private String username;
    private String userPhotoUrl;
    private String reviewText;
    private Date reviewDate;

    public Review() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (userID != review.userID) return false;
        if (carID != review.carID) return false;
        if (username != null ? !username.equals(review.username) : review.username != null) return false;
        if (userPhotoUrl != null ? !userPhotoUrl.equals(review.userPhotoUrl) : review.userPhotoUrl != null)
            return false;
        if (reviewText != null ? !reviewText.equals(review.reviewText) : review.reviewText != null) return false;
        return reviewDate != null ? reviewDate.equals(review.reviewDate) : review.reviewDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userID;
        result = 31 * result + carID;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userPhotoUrl != null ? userPhotoUrl.hashCode() : 0);
        result = 31 * result + (reviewText != null ? reviewText.hashCode() : 0);
        result = 31 * result + (reviewDate != null ? reviewDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userID=" + userID +
                ", carID=" + carID +
                ", username='" + username + '\'' +
                ", userPhotoUrl='" + userPhotoUrl + '\'' +
                ", reviewText='" + reviewText + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
