package com.example.proiectdam;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Game.class},version = 1,exportSchema = false)
public abstract class GameDB extends RoomDatabase{

    private static GameDB instance;
    private static String dbName = "games.db";

    public static GameDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,GameDB.class,dbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract GameDAO getGameDAO();

}
