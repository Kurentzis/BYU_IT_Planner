package com.example.BYU_IT_Planner.ui.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.BYU_IT_Planner.R;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    int pos;

    public EventAdapter(Context context, List<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Event event = getItem(position);



        if (convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        TextView title =convertView.findViewById(R.id.cellTitleEvent);
        TextView description = convertView.findViewById(R.id.cellDescEvent);
        TextView time = convertView.findViewById(R.id.cellTimeEvent);
        TextView location = convertView.findViewById(R.id.cellLocationEvent);


        title.setText(event.getTitleVal());
        description.setText(event.getDeskVal());
        time.setText(event.getDateVal().toString());
        location.setText(event.getEventLocVal());

        // delete.setTooltipText("Delete");
        // delete.setTag("delete");





        return convertView;


    }
}
