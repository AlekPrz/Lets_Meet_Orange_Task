package com.orange.letsMeet.model;

import java.text.SimpleDateFormat;
import java.util.*;

public class Calendar {


    private Date workTimeStart;
    private Date workTimeEnd;
    public static long meetingDuration = 30;
    private String calendarName;
    private List<Meet> plannedMeetings = new ArrayList<>();

    //Range of times when we have time for meeting
    private List<Meet> timeForMeetings = new ArrayList<>();


    public Calendar() {

    }


    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public Date getWorkTimeStart() {
        return workTimeStart;
    }

    public void setWorkTimeStart(Date workTimeStart) {
        this.workTimeStart = workTimeStart;
    }

    public Date getWorkTimeEnd() {
        return workTimeEnd;
    }

    public void setWorkTimeEnd(Date workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }


    public List<Meet> getTimeForMeetings() {
        return timeForMeetings;
    }


    public void setTimeForMeetings(List<Meet> timeForMeetings) {
        this.timeForMeetings = timeForMeetings;
    }

    public List<Meet> getPlannedMeetings() {
        return plannedMeetings;
    }

    public void setPlannedMeetings(List<Meet> plannedMeetings) {
        this.plannedMeetings = plannedMeetings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(workTimeStart, calendar.workTimeStart) && Objects.equals(workTimeEnd, calendar.workTimeEnd) && Objects.equals(plannedMeetings, calendar.plannedMeetings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workTimeStart, workTimeEnd, plannedMeetings);
    }

    @Override
    public String toString() {

        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");

        return "Calendar{" +
                "workTimeStart=" + date_format.format(workTimeStart) +
                ", workTimeEnd=" + date_format.format(workTimeEnd) +
                ", plannedMeetings=" + plannedMeetings +
                '}';
    }
}

