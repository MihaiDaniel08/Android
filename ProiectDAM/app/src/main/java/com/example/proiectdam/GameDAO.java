package com.example.proiectdam;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDAO {

    @Insert
    void insertGame(Game game);

    @Query("SELECT * FROM GAMES")
    List<Game> getAllGames();

    @Query("SELECT COUNT(*) FROM GAMES")
    int getCountGames();

    @Delete
    void deleteGame(Game game);
}
