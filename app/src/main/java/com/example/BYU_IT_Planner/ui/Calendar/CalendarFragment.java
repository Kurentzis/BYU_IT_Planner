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



    private Cursor cursor;
    Context context;
    Calendar cal;
    Calendar clickedDayCalendar;
    CalendarViewModel calendarViewModel;

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
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_CALENDAR) ==
                PackageManager.PERMISSION_GRANTED) {
            EventCollector eventCollector = new EventCollector(getContext(), eventHandler, this, mCalendarView);
            Thread threadGetTemp = new Thread(eventCollector);
            threadGetTemp.start();

        } else {
            requestCalPermission();
        }

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
                     Toast.makeText(getContext(), "Thank you!", Toast.LENGTH_LONG).show();
                 }
             })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                             dialogInterface.dismiss();
                            Toast.makeText(getContext(), "Permission is not granted", Toast.LENGTH_LONG).show();

                        }
                    })
                    .create().show();
        }
        else{
            ActivityCompat.requestPermissions(this.requireActivity(), new String[] {Manifest.permission.READ_CALENDAR}, CALENDAR_PERMISSION_CODE);
        }



}


}