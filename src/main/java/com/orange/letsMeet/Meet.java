package com.orange.letsMeet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Meet {

    private Date start;
    private Date end;

    public Meet(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Meet(){

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

    @Override
    public String toString() {

        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");

        return "Meet{" +
                "start=" + date_format.format(start) +
                ", end=" + date_format.format(end) +
                '}';
    }
}
