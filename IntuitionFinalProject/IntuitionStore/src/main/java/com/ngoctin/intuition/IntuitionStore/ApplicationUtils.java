package com.ngoctin.intuition.IntuitionStore;

import java.util.Date;

public class ApplicationUtils {

    public static String getCurrentTime(){
        Date date = new Date();
        int day = date.getDate();
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;
        int hour = date.getHours();
        int minute = date.getMinutes();
        int second = date.getSeconds();

        String stringDay = getFormatTimeString(day);
        String stringMonth = getFormatTimeString(month);
        String stringYear = getFormatTimeString(year);
        String stringHour = getFormatTimeString(hour);
        String stringMinute = getFormatTimeString(minute) ;
        String stringSecond = getFormatTimeString(second);

        String currTime = stringDay + "-" + stringMonth + "-" + stringYear
                + " " + stringHour +":"+stringMinute + ":" + stringSecond;
        return currTime;
    }

    public static String getFormatTimeString(int number){
        if(number < 10){
            return ("0"+number);
        }
        return (number + "");
    }



}
