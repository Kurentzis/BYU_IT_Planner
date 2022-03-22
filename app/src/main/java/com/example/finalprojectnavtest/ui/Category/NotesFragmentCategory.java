package com.example.finalprojectnavtest.ui.Category;

import java.util.ArrayList;

public class NotesFragmentCategory  {

    public static ArrayList<NotesFragmentCategory> notes_frag = new ArrayList<>();

    private String title;
    private String description;
    private String label;
    private String code;
    private int id;



    public NotesFragmentCategory(String title, String description, String label, String code, int id) {
        this.title = title;
        this.description = description;
        this.label = label;
        this.code = code;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setId(String code) {
        this.code = code;
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