package com.example.BYU_IT_Planner.ui.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.CalendarContract;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.BYU_IT_Planner.MainActivity;
import com.example.BYU_IT_Planner.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventCollector implements Runnable{
    Cursor cursor;
    Context context;
    EventHandler eventHandler;
    public List<EventDay> events = new ArrayList<>();
    List<Calendar> calendars = new ArrayList<>();
    public List<Event> eventsList = new ArrayList<>();
    public CalendarView mCalendarView;
    CalendarFragment calendarFragment;
    MainActivity mainActivity;


    public EventCollector(Context context, EventHandler eventHandler, CalendarFragment calendarFragment, CalendarView mCalendarView) {
        this.context = context;
        this.eventHandler = eventHandler;
        this.calendarFragment = calendarFragment;
        this.mCalendarView = mCalendarView;
    }

    @Override
    public void run() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            cursor = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, null, null, null);
        }

        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (cursor != null) {

                    Calendar cal = Calendar.getInstance();

                    //int id_1 = cursor.getColumnIndex(CalendarContract.Events._ID);
                   // int id_2 = cursor.getColumnIndex(CalendarContract.Events.TITLE);
                   // int id_3 = cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION);
                   // int id_4 = cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION);
                    int id_5 = cursor.getColumnIndex(CalendarContract.Events.DTSTART);
                    int id_6 = cursor.getColumnIndex(CalendarContract.Events.ACCOUNT_TYPE);
                    //int res = id_5 / (60*60*1000);


                    //String idValue = cursor.getColumnName(id_1);
                    //String titleValue = cursor.getString(id_2);
                    //String descriptionValue = cursor.getString(id_3);
                   // String eventValue = cursor.getString(id_4);
                   // String dateValue = cursor.getString(id_5);
                    long time = cursor.getLong(id_5);
                    Date date = new Date();
                    date.setTime(time);
                    date.getDate();
                    cal.setTimeInMillis(time);


                    events.add(new EventDay(cal, R.drawable.ic_baseline_circle_24));
                   // mCalendarView.setEvents(events);


                }

            }
            calendarFragment.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mCalendarView.setEvents(events);
                }
            });
        }


}}



