package com.example.BYU_IT_Planner.ui.AddNote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.BYU_IT_Planner.App;
import com.example.BYU_IT_Planner.R;

public class EditNote extends AppCompatActivity {

    private EditText titleEditText, descEditText, labelText, codeText;
    //private AddNoteFragment addNoteFragment = new AddNoteFragment();
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
            codeText = findViewById(R.id.newCode);
            titleEditText.setText(intent.getStringExtra("title"));
            labelText.setText(intent.getStringExtra("label"));
            descEditText.setText(intent.getStringExtra("description"));
            codeText.setText(intent.getStringExtra("code"));
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
        String code = codeText.getText().toString().trim();
        int newId = id;


        //TODO: get note id
        //int id = Note.noteList.size();

        //TODO: create an instance of a new note and add to a list

        Note note = new Note(newId, title, description, label, code);
        App.getInstance().getNoteDao().update(note);

        //Note.noteList.add(note);

        //Note.noteList.set(id, note);




        //TODO: finish the activity
        finish();

    }

}