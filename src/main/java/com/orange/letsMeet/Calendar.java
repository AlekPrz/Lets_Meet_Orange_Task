package com.orange.letsMeet;

import java.text.SimpleDateFormat;
import java.util.*;

public class Calendar {


    private Date workTimeStart;
    private Date workTimeEnd;
    private static String meetingDurationTime = "00:30";
    private List<Meet> plannedMeetings = new ArrayList<>();

    public Calendar(Date workTimeStart, Date workTimeEnd, List<Meet> plannedMeetings) {
        this.workTimeStart = workTimeStart;
        this.workTimeEnd = workTimeEnd;
        this.plannedMeetings = plannedMeetings;
    }

    public Calendar(){

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

    public static String getMeetingDurationTime() {
        return meetingDurationTime;
    }

    public static void setMeetingDurationTime(String meetingDurationTime) {
        Calendar.meetingDurationTime = meetingDurationTime;
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

