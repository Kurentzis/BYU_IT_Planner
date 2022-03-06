package com.example.finalprojectnavtest.ui.AddNote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalprojectnavtest.R;

public class ObserveNote extends AppCompatActivity {
    private TextView title, category, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observe_note);

        title = findViewById(R.id.placeForTitle);
        category = findViewById(R.id.placeForCategory);
        description = findViewById(R.id.placeForDescription);

        Intent intent = getIntent();
        String t = intent.getStringExtra("title");
        String c = intent.getStringExtra("label");
        String d = intent.getStringExtra("description") ;

        title.setText(t);
        category.setText(c);
        description.setText(d);
    }
}