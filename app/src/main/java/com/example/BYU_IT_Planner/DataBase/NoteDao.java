package com.example.BYU_IT_Planner.DataBase;

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

    @Query("SELECT * FROM note WHERE id IN(:noteIds)")
    List<Note> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM note WHERE title MATCH :title AND " + "label LIKE :label AND "+
            "description LIKE :description AND " + "code LIKE :code")
    Note findByTitle(String title, String label, String description, String code);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note...notes);
}
