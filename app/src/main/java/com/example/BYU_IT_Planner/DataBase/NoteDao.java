package com.example.BYU_IT_Planner.DataBase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.BYU_IT_Planner.ui.AddNote.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllLiveData();

    @NonNull
    @Query("SELECT * FROM note WHERE id = :id")
    Note getId(int id);

    @Query("SELECT * FROM note WHERE title LIKE :title")
    List<Note> findByTitle(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note...notes);
}
