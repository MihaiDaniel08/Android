package com.example.p1;

public class Review {
    private String userId;
    private String gameId;
    private String content;
    private int rating;

    // Constructor
    public Review(String userId, String gameId, String content, int rating) {
        this.userId = userId;
        this.gameId = gameId;
        this.content = content;
        this.rating = rating;
    }

    // Getters and Setters
    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}