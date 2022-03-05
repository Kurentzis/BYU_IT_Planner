package com.example.finalprojectnavtest.ui.AddNote;



import android.content.Intent;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.finalprojectnavtest.MainActivity;

import java.util.ArrayList;
import java.util.Objects;

public class AddNoteViewModel extends ViewModel {

    private MutableLiveData<ArrayList> mText;

    public AddNoteViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue(Note.noteList);
    }

    public LiveData<ArrayList> getText() {
        return mText;
    }


}