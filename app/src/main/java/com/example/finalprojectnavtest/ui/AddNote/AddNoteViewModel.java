package com.example.finalprojectnavtest.ui.AddNote;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.finalprojectnavtest.App;

import java.util.ArrayList;
import java.util.List;

public class AddNoteViewModel extends ViewModel {

    private MutableLiveData<ArrayList> mText;

    private LiveData<List<Note>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();


    public AddNoteViewModel() {


        mText = new MutableLiveData<>();
        mText.setValue(Note.noteList);
    }

    //public LiveData<ArrayList> getText() {
       // return mText;
    //}
    public LiveData<List<Note>> getText(){
        return noteLiveData;
    }


}