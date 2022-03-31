package com.example.BYU_IT_Planner.ui.AddNote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.BYU_IT_Planner.App;
import com.example.BYU_IT_Planner.R;

import java.util.List;

public class TestNoteActivity extends AppCompatActivity {

    private EditText titleEditText, descEditText, labelText, codeText;
    private AddNoteFragment addNoteFragment = new AddNoteFragment();
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_note);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        intent = getIntent();
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

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<Note> noteList = App.getInstance().getNoteDao().getAll();
                Note note = new Note( title, description, label, code);
                App.getInstance().getNoteDao().insert(note);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();



       // List<Note> noteList = App.getInstance().getNoteDao().getAll();



       // Note note = new Note( title, description, label, code);


       // App.getInstance().getNoteDao().insert(note);
        finish();



            //Note.noteList.add(note);
       // LiveData<List<Note>> noteList;







        //TODO: finish the activity


    }


}