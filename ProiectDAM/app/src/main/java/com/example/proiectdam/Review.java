package com.example.proiectdam;

import java.io.Serializable;

public class Review implements Serializable {
    private String userId;
    private String gameId;
    private String content;
    private float rating;

    public Review(String userId, String gameId, String content, float rating) {
        this.userId = userId;
        this.gameId = gameId;
        this.content = content;
        this.rating = rating;
    }

    public float getRating() {
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

    @Override
    public String toString() {
        return "Review{" +
                "userId='" + userId + '\'' +
                ", gameId='" + gameId + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}