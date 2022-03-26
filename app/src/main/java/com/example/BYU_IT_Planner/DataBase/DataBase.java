package com.example.BYU_IT_Planner.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.BYU_IT_Planner.ui.AddNote.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract NoteDao noteDao();

}
