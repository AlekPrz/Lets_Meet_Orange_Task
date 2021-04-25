package com.orange.letsMeet;

public class MenuService {


    private final JsonReader jsonReader = new JsonReader();
    private final Calendar calendar1;
    private final Calendar calendar2;


    public MenuService(String fileName1, String fileName2) {
        calendar1 = jsonReader.fromJsonCalendarToCalendar(fileName1);
        calendar2 = jsonReader.fromJsonCalendarToCalendar(fileName2);
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
                        System.out.println("2");
                        break;
                    case 3:
                        System.out.println("3");
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
        System.out.println("1 - Print free time for 30 min meeting");
        System.out.println("2 - Change time of meeting duration");
        System.out.println("3 - Close program");
        return UserDataService.getInt();
    }

    private void option1() {
        CalendarService calendarService = new CalendarService(calendar1, calendar2);
        calendarService.freeTimeForMeetings();
    }
}



