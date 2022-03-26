package com.example.BYU_IT_Planner.ui.Calendar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.BYU_IT_Planner.MainActivity;
import com.example.BYU_IT_Planner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CalendarFragment extends Fragment {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMMM", Locale.getDefault());
    //private CalendarView mCalendarView;
    private View view;
    public CalendarView mCalendarView;
    public List<EventDay> events = new ArrayList<>();
    List<Calendar> calendars = new ArrayList<>();
    public List<Event> eventsList = new ArrayList<>();


    private Cursor cursor;
    Context context;
    Calendar cal;
    Calendar clickedDayCalendar;
    CalendarViewModel calendarViewModel;
    EventAdapter eventAdapter;
    ListView lw;
    EventHandler eventHandler;
    MainActivity mainActivity;
    Button viewNote;

    private int CALENDAR_PERMISSION_CODE = 1;

    int res;


    private TextView month2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //initWidgets(getContext());
        context = getContext();
        cal = Calendar.getInstance();


        view = inflater.inflate(R.layout.fragment_calendar, null);
        mCalendarView = view.findViewById(R.id.calendar_view);
        mCalendarView.setHeaderColor(R.color.teal_700);
        lw = view.findViewById(R.id.placeForEvents);

        viewNote = view.findViewById(R.id.view_calendar);


        long time = cal.getTimeInMillis();


        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDayClick(EventDay eventDay) {
                clickedDayCalendar = eventDay.getCalendar();
                Intent intent2 = new Intent(Intent.ACTION_EDIT);
                intent2.setType("vnd.android.cursor.item/event");


                intent2.putExtra("allDay", false);
                intent2.putExtra("beginTime", clickedDayCalendar.getTimeInMillis());
                intent2.putExtra("rrule", "FREQ=YEARLY");
                intent2.putExtra("endTime", clickedDayCalendar.getTimeInMillis());
                intent2.putExtra("title", "What's the event?");
                startActivity(intent2);


                //Toast.makeText(getContext(), clickedDayCalendar.getTime().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        viewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long startMillis = cal.getTimeInMillis();

                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(builder.build());
                startActivity(intent);
            }
        });


        return view;
    }


    public void onResume() {
        super.onResume();


        //EventCollector eventCollector = new EventCollector(getContext(), eventHandler, this, mCalendarView);
        //Thread threadGetTemp = new Thread(eventCollector);
        //threadGetTemp.start();
        // Runnable r = new Runnable() {
        //   @Override
        //  public void run() {

        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_CALENDAR) ==
                PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                cursor = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, null, null, null);
            }

            while (cursor.moveToNext()) {
                if (cursor != null) {

                    Calendar cal = Calendar.getInstance();

                    int id_1 = cursor.getColumnIndex(CalendarContract.Events._ID);
                    int id_2 = cursor.getColumnIndex(CalendarContract.Events.TITLE);
                    int id_3 = cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION);
                    int id_4 = cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION);
                    int id_5 = cursor.getColumnIndex(CalendarContract.Events.DTSTART);
                    int id_6 = cursor.getColumnIndex(CalendarContract.Events.ACCOUNT_TYPE);
                    //int res = id_5 / (60*60*1000);


                    String idValue = cursor.getColumnName(id_1);
                    String titleValue = cursor.getString(id_2);
                    String descriptionValue = cursor.getString(id_3);
                    String eventValue = cursor.getString(id_4);
                    String dateValue = cursor.getString(id_5);
                    long time = cursor.getLong(id_5);
                    Date date = new Date();
                    date.setTime(time);
                    date.getDate();
                    cal.setTimeInMillis(time);


                    events.add(new EventDay(cal, R.drawable.ic_baseline_circle_24));
                    mCalendarView.setEvents(events);

                    //Event event = new Event(idValue, titleValue, descriptionValue, eventValue, date);
                    //eventsList.add(event);
                    // String size = String.valueOf(eventsList.get(0).titleVal);
                    // Toast.makeText(getContext(), size, Toast.LENGTH_SHORT).show();

                    //eventAdapter = new EventAdapter(Objects.requireNonNull(getContext()).getApplicationContext(), eventsList);
                    //lw.setAdapter(eventAdapter);


                } else {
                    Toast.makeText(getContext(), "No events", Toast.LENGTH_LONG).show();
                }


            }
        } else {
            requestCalPermission();
        }

        // if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
        //    ActivityCompat.requestPermissions(this.requireActivity(), new String[] {Manifest.permission.READ_CALENDAR}, getTargetRequestCode());

        // }







        // eventAdapter = new EventAdapter(Objects.requireNonNull(getContext()).getApplicationContext(), eventsList);
        //lw.setAdapter(eventAdapter);

        //  }

        //  };
        //  Handler handler = new Handler();
        //  handler.postDelayed(r, 1);




/*
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            cursor = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, null, null, null);
        }
        while (cursor.moveToNext()) {
            if (cursor != null) {
                Calendar cal = Calendar.getInstance();
                int id_1 = cursor.getColumnIndex(CalendarContract.Events._ID);
                int id_2 = cursor.getColumnIndex(CalendarContract.Events.TITLE);
                int id_3 = cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION);
                int id_4 = cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION);
                int id_5 = cursor.getColumnIndex(CalendarContract.Events.DTSTART);
                int id_6 = cursor.getColumnIndex(CalendarContract.Events.ACCOUNT_TYPE);
                //int res = id_5 / (60*60*1000);
                String idValue = cursor.getColumnName(id_1);
                String titleValue = cursor.getString(id_2);
                String descriptionValue = cursor.getString(id_3);
                String eventValue = cursor.getString(id_4);
                String dateValue = cursor.getString(id_5);
                long time = cursor.getLong(id_5);
                Date date = new Date();
                date.setTime(time);
                date.getDate();
                cal.setTimeInMillis(time);
                events.add(new EventDay(cal, R.drawable.ic_baseline_edit_24));
                mCalendarView.setEvents(events);
                Event event = new Event(idValue, titleValue, descriptionValue, eventValue, date);
                eventsList.add(event);
               // String size = String.valueOf(eventsList.get(0).titleVal);
               // Toast.makeText(getContext(), size, Toast.LENGTH_SHORT).show();
               // eventAdapter = new EventAdapter(Objects.requireNonNull(getContext()).getApplicationContext(), eventsList);
                //lw.setAdapter(eventAdapter);
                //Toast.makeText(getContext(), events.size(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "No events", Toast.LENGTH_LONG).show();
            }
        }
        String size = String.valueOf(eventsList.size());
       // eventAdapter = new EventAdapter(Objects.requireNonNull(getContext()).getApplicationContext(), eventsList);
        //lw.setAdapter(eventAdapter);
        Toast.makeText(getContext(), size, Toast.LENGTH_SHORT).show();
      /* calendarViewModel.getText().observe(Objects.requireNonNull(this), new Observer<ArrayList>() {
            @Override
            @NonNull
            public void onChanged(ArrayList arrayList)  {
                eventAdapter = new EventAdapter(Objects.requireNonNull(getContext()).getApplicationContext(), arrayList);
                lw.setAdapter(eventAdapter);
            }
        });*/
    }


    private void requestCalPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(), Manifest.permission.READ_CALENDAR)){
            new AlertDialog.Builder(this.requireActivity())
            .setTitle("Permission needed")
            .setMessage("This permission is needed to mark calendar with your events")
             .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[] {Manifest.permission.READ_CALENDAR}, CALENDAR_PERMISSION_CODE);
                 }
             })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                             dialogInterface.dismiss();
                            Toast.makeText(getContext(), "Permission is not granted", Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[] {Manifest.permission.READ_CALENDAR}, getTargetRequestCode());
                        }
                    })
                    .create().show();
        }
        else{
            ActivityCompat.requestPermissions(this.requireActivity(), new String[] {Manifest.permission.READ_CALENDAR}, CALENDAR_PERMISSION_CODE);
        }



}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CALENDAR_PERMISSION_CODE) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(), "Thank you!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getContext(), "Permission is not granted", Toast.LENGTH_LONG).show();
            }
        }
    }
}