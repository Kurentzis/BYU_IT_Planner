package com.example.finalprojectnavtest.ui.AddNote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finalprojectnavtest.R;

public class EditNote extends AppCompatActivity {

    private EditText titleEditText, descEditText, labelText;
    private AddNoteFragment addNoteFragment = new AddNoteFragment();
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        initWidgets();

    }

    private void initWidgets() {
        Intent intent = getIntent();
        if(intent != null){
            titleEditText = findViewById(R.id.newTitle);
            descEditText = findViewById(R.id.newDescription);
            labelText = findViewById(R.id.newLabel);
            titleEditText.setText(intent.getStringExtra("title"));
            labelText.setText(intent.getStringExtra("label"));
            descEditText.setText(intent.getStringExtra("description"));
            id = intent.getIntExtra("id", 0);
        }
        else{
            titleEditText = findViewById(R.id.title);
            descEditText = findViewById(R.id.description);
            labelText = findViewById(R.id.label);
        }

    }

    public void saveChanges(View view){
        //TODO: read fields of title and desc
        String title= titleEditText.getText().toString().trim();
        String description =  descEditText.getText().toString().trim();
        String label =  labelText.getText().toString().trim();
        int newId = id;


        //TODO: get note id
        //int id = Note.noteList.size();

        //TODO: create an instance of a new note and add to a list

        Note note = new Note(newId, title, description, label);

        //Note.noteList.add(note);

        Note.noteList.set(id, note);




        //TODO: finish the activity
        finish();

    }

}