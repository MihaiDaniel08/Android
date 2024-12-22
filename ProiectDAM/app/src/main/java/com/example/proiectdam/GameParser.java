package com.example.proiectdam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameParser {
    private static final String ID ="id";
    private static final String TITLE="title";
    private static final String DESCRIPTION="description";
    private static final String GENRE="genre";

    public static List<Game> parsareJson(String json){
        try{
            JSONArray jsonArray = new JSONArray(json);
            return parsareGames(jsonArray);
        } catch ( JSONException e){
            throw new RuntimeException(e);
        }
    }

    private static List<Game> parsareGames(JSONArray jsonArray) throws JSONException {
        List<Game> games = new ArrayList<>();
        for(int i=0; i< jsonArray.length(); i++){
            games.add(parsareGame(jsonArray.getJSONObject(i)));
        }
        return games;
    }
    private static Game parsareGame(JSONObject jsonObject) throws JSONException {
        String id = jsonObject.getString(ID);
        String title = jsonObject.getString(TITLE);
        String description = jsonObject.getString(DESCRIPTION);
        String genre = jsonObject.getString(GENRE);
        return new Game(id,title,description,genre);
    }
}
