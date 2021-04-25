package com.orange.letsMeet;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class JsonReader {

    public Calendar fromJsonCalendarToCalendar(String fileName) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(fileName)) {

            CalendarJson calendarJson = gson.fromJson(reader, CalendarJson.class);

            return convertToCalendar(calendarJson);

        } catch (IOException e) {
            throw new RuntimeException("Problem with calendar file");
        }
    }


    public Calendar convertToCalendar(CalendarJson calendarJson) {

        if (calendarJson == null) {
            throw new RuntimeException("Problem with calendarJson");
        }

        Calendar calendar = new Calendar();

        for (Map.Entry<String, String> tmp : calendarJson.getWorking_hours().entrySet()) {
            if (tmp.getKey().equals("start")) {
                calendar.setWorkTimeStart(UserDataService.parseDate(tmp.getValue()));
            }
            if (tmp.getKey().equals("end")) {
                calendar.setWorkTimeEnd(UserDataService.parseDate(tmp.getValue()));
            }
        }

        for (Map<String, String> tmp : calendarJson.getPlanned_meeting()) {
            Meet meet = new Meet();
            for (Map.Entry<String, String> entry : tmp.entrySet()) {
                if (entry.getKey().equals("start")) {
                    meet.setStart(UserDataService.parseDate(entry.getValue()));
                }
                if (entry.getKey().equals("end")) {
                    meet.setEnd(UserDataService.parseDate(entry.getValue()));
                }
            }

            calendar.getPlannedMeetings().add(meet);

        }

        return calendar;

    }
}
