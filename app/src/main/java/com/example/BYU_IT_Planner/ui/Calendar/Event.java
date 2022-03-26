package com.example.BYU_IT_Planner.ui.Calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    public List<Event> eventsList = new ArrayList<>();

    String idVal;
    String titleVal;
    String deskVal;
    String eventLocVal;
    Date dateVal;

    public Event(String idValue, String titleValue, String descriptionValue, String eventValue, Date dateValue) {
        this.idVal = idValue;
        this.titleVal = titleValue;
        this.deskVal = descriptionValue;
        this.eventLocVal = eventValue;
        this.dateVal = dateValue;
    }

    public String getIdVal() {
        return idVal;
    }

    public void setIdVal(String idVal) {
        this.idVal = idVal;
    }

    public String getTitleVal() {
        return titleVal;
    }

    public void setTitleVal(String titleVal) {
        this.titleVal = titleVal;
    }

    public String getDeskVal() {
        return deskVal;
    }

    public void setDeskVal(String deskVal) {
        this.deskVal = deskVal;
    }

    public String getEventLocVal() {
        return eventLocVal;
    }

    public void setEventLocVal(String eventLocVal) {
        this.eventLocVal = eventLocVal;
    }

    public Date getDateVal() {
        return dateVal;
    }

    public void setDateVal(Date dateVal) {
        this.dateVal = dateVal;
    }
}
