package com.example.finalprojectnavtest.ui.AddNote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.finalprojectnavtest.App;
import com.example.finalprojectnavtest.DataBase.DataBase;
import com.example.finalprojectnavtest.R;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;;
import java.util.List;
import java.util.Objects;


public class AddNoteFragment extends Fragment {
    private static NoteAdapter noteAdapter;
    private ListView lw;
    private View v;
    private AddNoteViewModel addNoteViewModel;






    public View onCreateView(@NotNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_addnote, container, false);
        lw = v.findViewById(R.id.listViewForNotes);


        Button button =  v.findViewById(R.id.buttonAddNote);
        addNoteViewModel = new ViewModelProvider(requireActivity()).get(AddNoteViewModel.class);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TestNoteActivity.class);
                AddNoteFragment.this.startActivity(intent);
            }
        });




        return v;
    }




    public void onResume(){
        super.onResume();
        addNoteViewModel.getText().observe(Objects.requireNonNull(getActivity()), new Observer<List<Note>>() {

            @Override
            public void onChanged(List<Note> arrayList) {


                noteAdapter = new NoteAdapter(Objects.requireNonNull(getActivity()).getApplicationContext(), arrayList);


                lw.setAdapter(noteAdapter);


                lw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                        adb.setTitle("Delete?");
                        final int positionToRemove = position;
                        adb.setMessage("Are you sure you want to delete " + noteAdapter.getItem(positionToRemove).getTitle());
                        adb.setNegativeButton("Cancel", null);
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //arrayList.get(positionToRemove);
                                //arrayList.remove(positionToRemove);
                                App.getInstance().getNoteDao().delete(arrayList.get(positionToRemove));
                                //noteAdapter.notifyDataSetChanged();
                            }
                        });
                        adb.show();
                        return true;
                    }
                });

                lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getContext(), ObserveNote.class);
                        intent.putExtra("title", noteAdapter.getItem(i).getTitle());
                        intent.putExtra("label", noteAdapter.getItem(i).getLabel());
                        intent.putExtra("description", noteAdapter.getItem(i).getDescription());
                        intent.putExtra("code", noteAdapter.getItem(i).getCode());
                        intent.putExtra("id", i);
                        startActivity(intent);
                    }
                });


            }
        });
    }



}