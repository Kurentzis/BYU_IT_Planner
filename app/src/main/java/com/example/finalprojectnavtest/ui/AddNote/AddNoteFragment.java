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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderKt;

import com.example.finalprojectnavtest.MainActivity;
import com.example.finalprojectnavtest.R;
import com.example.finalprojectnavtest.databinding.FragmentAddnoteBinding;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


public class AddNoteFragment extends Fragment {
    private ListView lw;
    private View v;

    private AddNoteViewModel addNoteViewModel;
    private NoteAdapter noteAdapter;



    public View onCreateView(@NotNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        v = inflater.inflate(R.layout.fragment_addnote, container, false);
        lw = v.findViewById(R.id.listViewForNotes);
        Button button =  v.findViewById(R.id.buttonAddNote);
        addNoteViewModel = new ViewModelProvider(requireActivity()).get(AddNoteViewModel.class);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tv.setText("SayHi!");
                Intent intent = new Intent(getActivity(), TestNoteActivity.class);
                AddNoteFragment.this.startActivity(intent);


            }
        });




        return v;


    }
    public void onResume(){
        super.onResume();
        addNoteViewModel.getText().observe(getActivity(), new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList arrayList) {

                noteAdapter = new NoteAdapter(Objects.requireNonNull(getActivity()).getApplicationContext(), arrayList);
                lw.setAdapter(noteAdapter);

                lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                        adb.setTitle("Delete?");
                        adb.setMessage("Are you sure you want to delete " + position);
                        final int positionToRemove = position;
                        adb.setNegativeButton("Cancel", null);
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(positionToRemove);
                                noteAdapter.notifyDataSetChanged();
                            }});
                        adb.show();
                    }
                });
            }
        });
    }



}