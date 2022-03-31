package com.example.BYU_IT_Planner.ui.Category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.BYU_IT_Planner.App;
import com.example.BYU_IT_Planner.R;
import com.example.BYU_IT_Planner.ui.AddNote.Note;
import com.example.BYU_IT_Planner.ui.AddNote.NoteAdapter;
import com.example.BYU_IT_Planner.ui.AddNote.ObserveNote;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CategoryFragment extends Fragment {

    private Spinner spinner;
    private ListView note_list;
    private List<Note> noteList;

    private NoteAdapter noteAdapter;
    private CategoryAdapter adapterlist;
    ArrayAdapter<String> adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_category, null);
        spinner = view.findViewById(R.id.spinner);
        note_list = view.findViewById(R.id.tv_result);

        Set<String> labelSet = new HashSet<>();
        //List<String> descripSet = new LinkedList<>();
        //List<String> titleSet = new LinkedList<>();
        Set<String> descrptSet = new HashSet<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<Note> noteList = App.getInstance().getNoteDao().getAll();

                noteList  = App.getInstance().getNoteDao().getAll();
                for (Note note: noteList) {
                    labelSet.add(note.getLabel());
                    descrptSet.add(note.getDescription());
                }
                String [] labeArray = labelSet.toArray(new String[0]);

                adapter = new ArrayAdapter <String> (getContext(), R.layout.spinner_item, labeArray);
// Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner


                List<Note> finalNoteList = noteList;
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getContext().getApplicationContext(), "Seleccionado " + adapterView.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                        String selected = adapterView.getItemAtPosition(i).toString();


                        //For to collect the label and description into the new Class
                        //If it's the same label and description it's going to be in.
                        NotesFragmentCategory.notes_frag.clear();
                        for (Note note: finalNoteList) {
                            for (String d: descrptSet) {
                                if (selected.equals(note.getLabel()) && d.equals(note.getDescription())) {
                                    NotesFragmentCategory note_frag = new NotesFragmentCategory(note.getTitle(), note.getDescription(), note.getLabel(), note.getCode());
                                    NotesFragmentCategory.notes_frag.add(note_frag);
                                    //Toast.makeText(getContext().getApplicationContext(), "Dentro del if" + note_frag.hashCode(), Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                        //Sending values to the Adapter Listview
                        adapterlist = new CategoryAdapter(getContext(), R.layout.note_cell2, NotesFragmentCategory.notes_frag, selected);

                        //Show the notes on ListView
                        note_list.setAdapter(adapterlist);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });




            }
        };
        Thread thread = new Thread(runnable);
        thread.start();






        return view;



    }

    public void  onResume() {
        super.onResume();
        spinner.setAdapter(adapter);
        note_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ObserveNote.class);
                intent.putExtra("title", NotesFragmentCategory.notes_frag.get(i).getTitle());
                intent.putExtra("label", NotesFragmentCategory.notes_frag.get(i).getLabel());
                intent.putExtra("description", NotesFragmentCategory.notes_frag.get(i).getDescription());
                intent.putExtra("code", NotesFragmentCategory.notes_frag.get(i).getCode());
                intent.putExtra("id", i);
                intent.putExtra("del", true);
                startActivity(intent);
            }
        });
    }

}