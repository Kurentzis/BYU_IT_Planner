package com.example.BYU_IT_Planner.ui.AddNote;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.BYU_IT_Planner.App;

import java.util.List;

public class SearcnableActivity extends AppCompatActivity {
    AddNoteFragment addNoteFragment = new AddNoteFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    private void doSearch(String query) {
        List<Note> noteList = App.getInstance().getNoteDao().findByTitle(query);
        AddNoteFragment.noteAdapter.notifyDataSetChanged();

    }
}