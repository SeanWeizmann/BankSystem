package com.sean.bankSystem.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataUtil {

    public static Date localDateToSqlDate(LocalDate localDate){
        return java.sql.Date.valueOf(localDate);
    }

    public static LocalDate getStartDate(){
        return LocalDate.now().minusDays((int)(Math.random()*14)+1);
    }

    public static LocalDate getEndDate(){
        return LocalDate.now().plusDays((int)(Math.random()*14)+1);
    }

    public static String beautifyLocalDate(LocalDate localDate){
        return String.format("%02d/%02d/%04d",
                localDate.getDayOfMonth(),localDate.getMonthValue(),localDate.getYear());
    }

    public static String beautifyDateTime(LocalDateTime localDate){
        return String.format("%02d/%02d/%04d %02d:%02d:%02d",
                localDate.getDayOfMonth(),localDate.getMonthValue(),localDate.getYear(),
                localDate.getHour(),localDate.getMinute(),localDate.getSecond()
        );
    }

    public static String getLocalDateTime(){
        return "["+beautifyDateTime(LocalDateTime.now())+"]";
    }

    public static void main(String[] args) {
        System.out.println(getLocalDateTime());
    }

}
