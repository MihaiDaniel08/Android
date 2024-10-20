package com.example.p1;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Game> playedGames;
    private List<Review> reviews;

    // Constructor
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.playedGames = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    // Getters and Setters
    public void addGame(Game game) {
        playedGames.add(game);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    // Other logic methods
    public List<Game> getPlayedGames() {
        return playedGames;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}