package com.example.finalprojectnavtest.ui.AddNote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.finalprojectnavtest.App;

public class SearcnableActivity extends AppCompatActivity {

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
        App.getInstance().getNoteDao().findByTitle(query,query,query,query);

    }
}