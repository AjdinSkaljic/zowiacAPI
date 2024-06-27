package com.zowiac.commons;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    //add months to date
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    //get moth as String from date
    public static String getMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM YYYY");
        return sdf.format(date);
    }


    public static  String formatDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    //get list of quartals between two dates
    public static String getQuartal(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int quartal = (month / 3) + 1;
        return "Q" + quartal + " " + year;
    }

    public static List<String> getQuartals(Date from, Date to) {
        List<String> quartals = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int quartal = (month / 3) + 1;
        int i = 0;
        while (cal.getTime().before(to)) {
            quartals.add("Q" + quartal + " " + year);
            cal.add(Calendar.MONTH, 3);
            quartal = (cal.get(Calendar.MONTH) / 3) + 1;
            year = cal.get(Calendar.YEAR);
        }
        return quartals;
    }

    //get days as string list between two dates
    public static List<String> getDays(Date from, Date to) {
        List<String> days = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        while (cal.getTime().before(to)) {
            days.add(new SimpleDateFormat("dd.MM.yyyy").format(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }
        return days;
    }


    //count months between two dates
    public static int countMonths(Date from, Date to) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        int fromYear = cal.get(Calendar.YEAR);
        int fromMonth = cal.get(Calendar.MONTH);
        cal.setTime(to);
        int toYear = cal.get(Calendar.YEAR);
        int toMonth = cal.get(Calendar.MONTH);
        return (toYear - fromYear) * 12 + toMonth - fromMonth;
    }


}
