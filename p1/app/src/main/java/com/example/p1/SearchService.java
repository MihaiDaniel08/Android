package com.example.p1;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private List<Game> gameDatabase;

    public SearchService(List<Game> gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    public List<Game> searchByTitle(String title) {
        List<Game> result = new ArrayList<>();
        for (Game game : gameDatabase) {
            if (game.getTitle().equalsIgnoreCase(title)) {
                result.add(game);
            }
        }
        return result;
    }

    public List<Game> searchByGenre(String genre) {
        List<Game> result = new ArrayList<>();
        for (Game game : gameDatabase) {
            if (game.getGenre().equalsIgnoreCase(genre)) {
                result.add(game);
            }
        }
        return result;
    }
}