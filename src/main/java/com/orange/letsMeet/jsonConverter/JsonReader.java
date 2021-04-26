package com.orange.letsMeet.jsonConverter;

import com.google.gson.Gson;
import com.orange.letsMeet.model.Calendar;
import com.orange.letsMeet.model.Meet;
import com.orange.letsMeet.service.UserDataService;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class JsonReader {

    public static Calendar fromJsonCalendarToCalendar(String fileName) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(fileName)) {

            CalendarJson calendarJson = gson.fromJson(reader, CalendarJson.class);
            return convertToCalendar(calendarJson, fileName);

        } catch (IOException e) {
            throw new RuntimeException("Problem with calendar file");
        }
    }


    public static Calendar convertToCalendar(CalendarJson calendarJson, String fileName) {

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
            if (fileName.equals("calendar1.json")) {
                calendar.setCalendarName("calendar1");
            } else {
                calendar.setCalendarName("calendar2");
            }

        }

        for (Map<String, String> tmp : calendarJson.getPlanned_meeting()) {
            Meet meet = new Meet();
            meet.setCalendar(calendar);

            for (Map.Entry<String, String> entry : tmp.entrySet()) {
                if (entry.getKey().equals("start")) {
                    meet.setStart(UserDataService.parseDate(entry.getValue()));
                }
                if (entry.getKey().equals("end")) {
                    meet.setEnd(UserDataService.parseDate(entry.getValue()));
                }
            }

            //Validation

            if(meet.getStart().after(meet.getEnd())){
                throw new RuntimeException("Date of planned meeting start can't be after end!");
            }

            if(meet.getStart().before(calendar.getWorkTimeStart())){
                throw new RuntimeException("Date of planned meeting can't be before work start!");
            }
            if(meet.getEnd().after(calendar.getWorkTimeEnd())){
                throw new RuntimeException("Date of planned meeting can't be after work end!");
            }

            calendar.getPlannedMeetings().add(meet);

        }
        if(calendar.getWorkTimeStart().after(calendar.getWorkTimeEnd())){
            throw new RuntimeException("Date of work start can't be after end!");
        }

        return calendar;

    }
}
