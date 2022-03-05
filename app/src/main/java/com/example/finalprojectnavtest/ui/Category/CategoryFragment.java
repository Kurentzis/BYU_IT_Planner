package com.example.finalprojectnavtest.ui.Category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.finalprojectnavtest.R;
import com.example.finalprojectnavtest.ui.AddNote.Note;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class CategoryFragment extends Fragment {


    private TextView tv;
    private Spinner spinner;
    private ListView note_list;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_category, null);
        spinner = view.findViewById(R.id.spinner);
        note_list = view.findViewById(R.id.tv_result);

        Set<String> labelSet = new HashSet<>();
        List<String> descripSet = new LinkedList<>();
        List<String> titleSet = new LinkedList<>();

        for (Note note: Note.noteList) {
            labelSet.add(note.getLabel());
        }

        String [] labeArray = labelSet.toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter <String> (getContext(), android.R.layout.simple_spinner_item, labeArray);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getContext().getApplicationContext(), "Seleccionado " + adapterView.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                String selected = adapterView.getItemAtPosition(i).toString();

                //For to collect the Title and Description
                for (Note note: Note.noteList) {
                    if (selected.equals(note.getLabel())) {
                        descripSet.add(note.getDescription());
                        titleSet.add(note.getTitle());
                    }
                }
                //List to save the notes collected
                List<String> notes = new LinkedList<>();

                ArrayAdapter<String> list = new ArrayAdapter <String> (getContext(), android.R.layout.simple_spinner_item, notes);
                //For to collect the notes
                for (int j = 0; j <descripSet.size() ; j++) {
                    notes.add("Title: "+ titleSet.get(j)+ " "+ "Desciption: " +descripSet.get(j));

                }
                //Clean list of Description and Title
                descripSet.clear();
                titleSet.clear();

                //Show the notes on View
                note_list.setAdapter(list);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }


}