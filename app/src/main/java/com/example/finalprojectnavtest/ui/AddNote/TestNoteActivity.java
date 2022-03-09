package com.example.finalprojectnavtest.ui.AddNote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finalprojectnavtest.R;

import java.util.Objects;

public class TestNoteActivity extends AppCompatActivity {

    private EditText titleEditText, descEditText, labelText, codeText;
    private AddNoteFragment addNoteFragment = new AddNoteFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_note);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        initWidgets();



    }

    private void initWidgets() {



            titleEditText = findViewById(R.id.title);
            descEditText = findViewById(R.id.description);
            labelText = findViewById(R.id.label);
            codeText = findViewById(R.id.codeExample);


    }


    public void saveNote(View view){
        //TODO: read fields of title and desc
       String title= titleEditText.getText().toString().trim();
        String description =  descEditText.getText().toString().trim();
        String label =  labelText.getText().toString().trim();
        String code = codeText.getText().toString().trim();


        //TODO: get note id
        int id = Note.noteList.size();

        //TODO: create an instance of a new note and add to a list
        
        Note note = new Note(id, title, description, label, code);

            Note.noteList.add(note);

        //Note.noteList.add(note);


        //TODO: finish the activity
        finish();

    }

}