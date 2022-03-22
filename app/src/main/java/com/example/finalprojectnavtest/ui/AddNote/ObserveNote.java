package com.example.finalprojectnavtest.ui.AddNote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalprojectnavtest.App;
import com.example.finalprojectnavtest.R;

import java.util.List;

public class ObserveNote extends AppCompatActivity {
    private TextView title, category, description, code;
    private Button delete;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observe_note);

        title = findViewById(R.id.placeForTitle);
        category = findViewById(R.id.placeForCategory);
        description = findViewById(R.id.placeForDescription);
        delete = findViewById(R.id.deleteButton);
        code = findViewById(R.id.placeForCode);


        Intent intent = getIntent();
        String t = intent.getStringExtra("title");
        String c = intent.getStringExtra("label");
        String d = intent.getStringExtra("description") ;
        String co = intent.getStringExtra("code");
        id = intent.getIntExtra("id",0);
        boolean del = intent.getBooleanExtra("del", false);
        description.setMovementMethod(new ScrollingMovementMethod());
        code.setMovementMethod(new ScrollingMovementMethod());

        if (del){
            delete.setVisibility(View.GONE);
        }

        title.setText(t);
        category.setText(c);
        description.setText(d);
        code.setText(co);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ObserveNote.this);
                adb.setTitle("Delete?");
                final int positionToRemove = id;
                adb.setMessage("Are you sure you want to delete " + t);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //arrayList.get(positionToRemove);
                        //Note.noteList.remove(positionToRemove);
                        List<Note> noteList = App.getInstance().getNoteDao().getAll();

                        App.getInstance().getNoteDao().delete(noteList.get(positionToRemove));
                        finish();
                    }
                });
                adb.show();

            }
        });


    }


}