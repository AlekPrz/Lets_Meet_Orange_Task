package com.orange.letsMeet.service;

import com.orange.letsMeet.model.Calendar;
import com.orange.letsMeet.jsonConverter.JsonReader;
import com.orange.letsMeet.model.Meet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MenuService {


    private final Calendar calendar1;
    private final Calendar calendar2;


    public MenuService(String fileName1, String fileName2) {
        calendar1 = JsonReader.fromJsonCalendarToCalendar(fileName1);
        calendar2 = JsonReader.fromJsonCalendarToCalendar(fileName2);
    }

    public void mainMenu() {
        while (true) {
            try {
                int mainMenuOption = printMainMenu();
                switch (mainMenuOption) {
                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Option number is not valid");
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }

    private int printMainMenu() {
        System.out.println("Choice option");
        System.out.println("Time of meeting duration: " + Calendar.meetingDuration + " min");
        System.out.println("1 - Print free range times for meeting");
        System.out.println("2 - Change time of meeting duration");
        System.out.println("3 - Close program");
        return UserDataService.getInt();
    }

    private void option1() {
        CalendarService calendarService = new CalendarService(calendar1, calendar2);
        List<Meet> freeTimeAfterCompareCalendars =
                calendarService.combineTwoCalendars()
                        .stream()
                        .sorted(Comparator.comparing(Meet::getStart))
                        .collect(Collectors.toList());

        System.out.println("Free range times in work for " + Calendar.meetingDuration + "min meeting: ");
        System.out.println("----------------------------");
        if(freeTimeAfterCompareCalendars.isEmpty()){
            System.out.println("There is no free range times for meeting you need change your calendar or time of meeting duration");
        }
        freeTimeAfterCompareCalendars.forEach(System.out::println);
        System.out.println("----------------------------");


    }

    private void option2() {

        System.out.println("Insert new meeting time duration");
        long changeMeetingDuration = UserDataService.getInt();
        if (changeMeetingDuration <= 5) {
            throw new RuntimeException("Meeting duration time at least must be higher than 5min");
        }
        List<Calendar> calendars = new ArrayList<>();

        calendars.add(calendar1);
        calendars.add(calendar2);

        for (Calendar tmp : calendars) {
            long minutes = UserDataService.timeDifferenceBetween2Dates(tmp.getWorkTimeStart(), tmp.getWorkTimeEnd());
            if (changeMeetingDuration > minutes){
                throw new RuntimeException("Meeting duration time is higher than range time or working");
            }
        }


        Calendar.meetingDuration = changeMeetingDuration;


    }
}



