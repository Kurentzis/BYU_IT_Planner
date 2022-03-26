package com.example.BYU_IT_Planner.ui.Calendar;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class CalendarViewModel {
    private MutableLiveData<ArrayList<Event>> events;
    private  Event event;



    public void AddNoteViewModel() {


        events = new MutableLiveData<>();
        events.setValue((ArrayList<Event>) event.eventsList);
    }

    //public LiveData<ArrayList> getText() {
    // return mText;
    //}
    public MutableLiveData<ArrayList<Event>> getText(){
        return events;
    }
}
