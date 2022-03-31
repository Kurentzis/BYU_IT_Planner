package com.example.BYU_IT_Planner;

import android.app.Application;

import androidx.room.Room;

import com.example.BYU_IT_Planner.DataBase.DataBase;
import com.example.BYU_IT_Planner.DataBase.NoteDao;

public class App extends Application {
    private DataBase database;
    private NoteDao noteDao;

    private static App instance;


    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        DataBase  database = Room.databaseBuilder(getApplicationContext(),
                DataBase.class, "App-Data-Base-2")

                .build();

        noteDao = database.noteDao();





    }

    public DataBase getDatabase() {
        return database;
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }
}
