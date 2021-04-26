package com.orange.letsMeet.service;

import com.orange.letsMeet.model.Calendar;
import com.orange.letsMeet.model.Meet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalendarService {

    private Calendar calendar1;
    private Calendar calendar2;

    public CalendarService(Calendar calendar1, Calendar calendar2) {
        this.calendar1 = calendar1;
        this.calendar2 = calendar2;
    }


    public List<Meet> combineTwoCalendars() {


        List<Meet> firstCalendarWith5minRange = convertFreeTimeMeetingsInto5minRange(freeTimeForOneCalendar(calendar1));
        List<Meet> secondCalendarWith5minRange = convertFreeTimeMeetingsInto5minRange(freeTimeForOneCalendar(calendar2));

        //Get duplicate, now we know when 2 calendars have the same range free time
        firstCalendarWith5minRange.retainAll(secondCalendarWith5minRange);


        List<Meet> combine2CalendarsWith5MinutesDifference = new ArrayList<>(firstCalendarWith5minRange);
        List<Meet> final2CombinedCalendars = new ArrayList<>();

        for (int i = 1; i < combine2CalendarsWith5MinutesDifference.size(); ) {

            int counter = i;
            int howMany5minutesRange= 1;

                //Compare previous end meeting time with next start time meeting
            while (combine2CalendarsWith5MinutesDifference.get(counter - 1).getEnd().compareTo(combine2CalendarsWith5MinutesDifference.get(counter).getStart()) == 0) {
                counter++;
                howMany5minutesRange++;
                if (counter == combine2CalendarsWith5MinutesDifference.size()) {
                    break;
                }
            }
            long time = (long) howMany5minutesRange * 5 * 60000;

            if (TimeUnit.MILLISECONDS.toMinutes(time) >= Calendar.meetingDuration) {
                Date dateStart = combine2CalendarsWith5MinutesDifference.get(i - 1).getStart();
                Date dateEnd = new Date(dateStart.getTime() + time);
                final2CombinedCalendars.add(new Meet(dateStart, dateEnd));
            }
            counter++;
            i = counter;


        }
        return final2CombinedCalendars;

    }

    public List<Meet> convertFreeTimeMeetingsInto5minRange(List<Meet> tmp) {

        List<Meet> arrayListWith5minRange = new ArrayList<>();
        for (Meet pom : tmp) {
            Date startDate = pom.getStart();
            Date endDate = pom.getEnd();

            if (UserDataService.timeDifferenceBetween2Dates(startDate, endDate) >= Calendar.meetingDuration) {
                while (startDate.compareTo(endDate) <= 0) {
                    if (startDate.compareTo(endDate) == 0) {
                        break;
                    }
                    arrayListWith5minRange.add(new Meet(startDate, new Date(startDate.getTime() + (5 * 60000))));
                    startDate = new Date(startDate.getTime() + (5 * 60000));
                }
            }
        }

        return arrayListWith5minRange;

    }


    public List<Meet> freeTimeForOneCalendar(Calendar calendar) {


        Date workTimeStart = calendar.getWorkTimeStart();
        Date startOfFirstMeeting = calendar.getPlannedMeetings().get(0).getStart();

        List<Meet> timeForMeetings = new ArrayList<>();

        //Check if there is time between start of work  and first meeting
        if (UserDataService.timeDifferenceBetween2Dates(workTimeStart, startOfFirstMeeting) >= Calendar.meetingDuration) {
            timeForMeetings.add(new Meet(workTimeStart, startOfFirstMeeting, calendar));
        }

        //Check if there is time between meetings
        for (int i = 1; i < calendar.getPlannedMeetings().size(); i++) {
            Date endTimeOfPreviousMeeting = calendar.getPlannedMeetings().get(i - 1).getEnd();
            Date startTimeOfNextMeeeting = calendar.getPlannedMeetings().get(i).getStart();
            if (UserDataService.timeDifferenceBetween2Dates(endTimeOfPreviousMeeting, startTimeOfNextMeeeting) >= Calendar.meetingDuration) {
                timeForMeetings.add(new Meet(endTimeOfPreviousMeeting, startTimeOfNextMeeeting, calendar));
            }
        }

        Date workTimeEnd = calendar.getWorkTimeEnd();
        Date endOfLastMeeting = calendar.getPlannedMeetings().get(calendar.getPlannedMeetings().size() - 1).getEnd();
        if (UserDataService.timeDifferenceBetween2Dates(endOfLastMeeting, workTimeEnd) >= Calendar.meetingDuration) {
            timeForMeetings.add(new Meet(endOfLastMeeting, workTimeEnd, calendar));
        }
        calendar.setTimeForMeetings(timeForMeetings);
        return timeForMeetings;
    }


}
