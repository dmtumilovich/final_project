package by.epam.rentacar.entity;

import java.util.Date;

public class Review {

    private int id;
    private int userID;
    private String username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userID=" + userID +
                ", reviewText='" + reviewText + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
