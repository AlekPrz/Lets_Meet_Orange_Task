package com.orange.letsMeet;

import java.util.List;
import java.util.Map;

public class CalendarJson {

    private Map<String,String> working_hours;
    private List<Map<String,String>> planned_meeting;


    public Map<String, String> getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(Map<String, String> working_hours) {
        this.working_hours = working_hours;
    }

    public List<Map<String, String>> getPlanned_meeting() {
        return planned_meeting;
    }

    public void setPlanned_meeting(List<Map<String, String>> planned_meeting) {
        this.planned_meeting = planned_meeting;
    }
}
