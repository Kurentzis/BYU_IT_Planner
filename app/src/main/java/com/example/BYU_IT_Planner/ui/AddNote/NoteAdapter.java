package com.example.BYU_IT_Planner.ui.AddNote;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.BYU_IT_Planner.R;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
    }

    private  int pos;
    public ImageButton delete;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Note note = getItem(position);

      //  pos = note.getId();

        if (convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_cell, parent, false);
        TextView title =convertView.findViewById(R.id.cellTitle);
        TextView description = convertView.findViewById(R.id.cellDesc);
        TextView label = convertView.findViewById(R.id.cellLabel);
        delete = convertView.findViewById(R.id.deleteButton);

        title.setText(note.getTitle());
        description.setText(note.getDescription());
        label.setText(note.getLabel());

       // delete.setTooltipText("Delete");
       // delete.setTag("delete");

       delete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onClick(View view) {
                //addNoteFragment.deleteElement(delete, note);
               // NoteAdapter.super.remove(note);
               // NoteAdapter.super.notifyDataSetChanged();

                Intent intent = new Intent(getContext(), EditNote.class);
                intent.putExtra("title", note.getTitle());
                intent.putExtra("label", note.getLabel());
                intent.putExtra("description", note.getDescription());
              //  intent.putExtra("id", note.getId());
                intent.putExtra("code", note.getCode());
                intent.putExtra("id" , note.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().getApplicationContext().startActivity(intent);






            }

       });

        return convertView;


    }



}
