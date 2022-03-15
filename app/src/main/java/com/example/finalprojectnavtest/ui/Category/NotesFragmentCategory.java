package com.example.finalprojectnavtest.ui.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NotesFragmentCategory  {

    public static ArrayList<NotesFragmentCategory> notes_frag = new ArrayList<>();

    private String title;
    private String description;
    private String label;
    private int id;


    public NotesFragmentCategory(String title, String description, String label, int id) {
        this.title = title;
        this.description = description;
        this.label = label;
        this.id = id;
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
