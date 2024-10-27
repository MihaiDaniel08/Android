package com.example.proiectdam;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    private String id;
    private String title;
    private String description;
    private String genre;
    private float averageRating;
    private List<Review> reviews;

    public Game(String id, String title, String description, String genre) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.reviews = new ArrayList<>();
    }

    public float getAverageRating() {
        return averageRating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public void addReview(Review review) {
        reviews.add(review);
        updateAverageRating();
    }

    private void updateAverageRating() {
        int totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        this.averageRating = reviews.size() > 0 ? (float) totalRating / reviews.size() : 0;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}