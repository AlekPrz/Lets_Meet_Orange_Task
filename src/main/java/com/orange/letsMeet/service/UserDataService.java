package com.orange.letsMeet.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserDataService {

    private UserDataService(){}

    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(){
        String text = scanner.nextLine();
        if(!text.matches("\\d+")){
            throw new RuntimeException("Int value is not valid: " + text);
        }
        return Integer.parseInt(text);
    }

    public static Date parseDate(String date) {

        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("HH:mm");

        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Date is not valid!");
        }

    }

    public static long timeDifferenceBetween2Dates(Date startDate, Date endDate) {

        if (endDate.before(startDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }
        long timeDiff = endDate.getTime() - startDate.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff);

        return minutes;
    }

}
