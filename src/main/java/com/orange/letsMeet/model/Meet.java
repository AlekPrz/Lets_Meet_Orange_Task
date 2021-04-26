package com.orange.letsMeet.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Meet {

    private Date start;
    private Date end;
    //With this after combine 2 calendars i know which calendar who it belongs
    private Calendar calendar;

    public Meet(Date start, Date end, Calendar calendar) {
        this.start = start;
        this.end = end;
        this.calendar = calendar;
    }
    public Meet(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Meet() {

    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String toString() {

        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");

      /*  return "Meet{" +
                "start=" + date_format.format(start) +
                ", end=" + date_format.format(end) +
                '}';*/

        return "start:" + date_format.format(start) + " end:"
                + date_format.format(end);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meet meet = (Meet) o;
        return Objects.equals(start, meet.start) && Objects.equals(end, meet.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
