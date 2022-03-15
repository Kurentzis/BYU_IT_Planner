package com.example.finalprojectnavtest.ui.AddNote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectnavtest.MainActivity;
import com.example.finalprojectnavtest.R;

import org.json.JSONException;

import java.util.List;
import java.util.Objects;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
    }

    private static AddNoteFragment addNoteFragment;
    private  int pos;
    public ImageButton delete;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Note note = getItem(position);

        pos = note.getId();

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
                intent.putExtra("id", note.getId());
                intent.putExtra("code", note.getCode());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().getApplicationContext().startActivity(intent);






            }

       });

        return convertView;


    }



}
