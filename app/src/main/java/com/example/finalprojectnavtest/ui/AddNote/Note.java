package com.example.finalprojectnavtest.ui.AddNote;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Note {
    public static ArrayList<Note> noteList = new ArrayList<>();

    private int id;
    private String title;
    private String description;
    private String label;

    public Note(int id, String title, String description, String label) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
