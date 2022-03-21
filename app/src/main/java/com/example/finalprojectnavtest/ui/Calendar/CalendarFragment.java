package com.example.finalprojectnavtest.ui.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.finalprojectnavtest.R;
import com.example.finalprojectnavtest.databinding.FragmentCalendarBinding;

public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding binding;
    private CalendarView mCalendarView;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        initWidgets();

        view = inflater.inflate(R.layout.fragment_calendar, null);

        return view;
    }

    public void onResume() {
        super.onResume();
        mCalendarView = view.findViewById(R.id.calendar_view);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Toast.makeText(getContext(),
                        "Year: " + year + "\n" +
                                "Month: " + month + "\n" +
                                "Day: " + day,
                        Toast.LENGTH_SHORT).show();

            }
        });

    }



    private void initWidgets() {
       /* mCalendarView = binding.calendarView;
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Toast.makeText(getContext(),
                        "Год: " + year + "\n" +
                                "Месяц: " + month + "\n" +
                                "День: " + day,
                        Toast.LENGTH_SHORT).show();

            }
        });*/
    }

}