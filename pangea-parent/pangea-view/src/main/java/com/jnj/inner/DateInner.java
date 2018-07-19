package com.jnj.inner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by XZhan290 on 2018/4/18.
 */
public class DateInner {

    public final static String DATE_FORMAT_1 = "yyyy-MM-dd";
    public final static String F_yyyyMMdd = "yyyyMMdd";
    public final static String F_yyyyMMdd_2 = "yyyy/MM/dd";
    public final static String F_yyyyMMdd_3 = "dd.MM.yyyy";
    public final static String F_yyyyMM = "yyyyMM";
    public final static String F_hhmmss_a = "hh:mm:ss a";
    public final static String F_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public final static String yyyyMMdd_HHmmss = "yyyyMMdd_HH-mm-ss";
    public final static String yyyy_MM_dd_HHmmss_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
    public final static String yyyy_MM_dd_HHmmss_2 = "yyyy/MM/dd HH:mm:ss";
    public final static String yyyyMMdd_HHmmssSSS = "yyyyMMdd HH:mm:ss:SSS";
    public final static String yyyyMMdd_HHmmssSSS_2 = "yyyyMMdd HH:mm:ss";
    public final static String yyyy_MM_dd_HHmmssSSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String yyyy_MM_dd_HHmmss = "yyyy-MM-dd hh:mm:ss a";
    public final static String US_MMM_dd_yyyy_hhmmssSSSaa = "MMM dd yyyy hh:mm:ss:SSSaa";
    public final static String US_EEE_MMM_dd_hhmmsszyyyy = "EEE MMM dd HH:mm:ss z yyyy";
    public final static String dd_MM_yyyy_HHmmss_1 = "dd/MM/yyyy HH:mm:ss";
    public final static String dd_MM_yyyy_HHmmss_2 = "dd-MM-yyyy HH:mm:ss";
    public final static String yyyy_MM_dd_T_HHmmss_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static String yyyyMMddToyyyyMM(String dateStr) {
        if (dateStr == null || dateStr.length() != 8)
            return dateStr;

        Date date = stringToDate(dateStr, F_yyyyMMdd);
        if (date != null)
            return dateToString(date, F_yyyyMM);
        else
            return null;
    }


    public static String currentDate(String formatStr) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(now);
    }

    public static String dateToString(Date dt, String format) {
        return dateToString(dt, format, Locale.getDefault());
    }

    public static String dateToString(Date dt, String format, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(dt);
    }

    public static Date stringToDate(String dateStr, String format) {
        return stringToDate(dateStr, format, Locale.getDefault());
    }

    public static Date stringToDate(String dateStr, String format, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        Date s_date = null;
        try {
            s_date = (Date) sdf.parse(dateStr);
        } catch (ParseException e) {
            s_date = correctDate(dateStr, format);
        }
        return s_date;
    }

    public static Date offsetYear(Date date, int year) {
        return changeDate(date, year, 0, 0, 0, 0, 0);
    }

    public static Date offsetMonth(Date date, int month) {
        return changeDate(date, 0, month, 0, 0, 0, 0);
    }

    public static Date offsetDay(Date date, int day) {
        return changeDate(date, 0, 0, day, 0, 0, 0);
    }

    public static Date offsetHour(Date date, int hour) {
        return changeDate(date, 0, 0, 0, hour, 0, 0);
    }

    public static Date offsetMinute(Date date, int minute) {
        return changeDate(date, 0, 0, 0, 0, minute, 0);
    }

    public static Date offsetSecond(Date date, int second) {
        return changeDate(date, 0, 0, 0, 0, 0, second);
    }



    public static String StringToString (String dateStr, String format, String targetFormat, Locale locale){
        Date date = stringToDate(dateStr, format, locale);
        return dateToString(date, targetFormat, locale);
    }



    public static Date changeDate (Date date,int year, int month, int day, int hour, int minute, int second){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
        return calendar.getTime();
    }

    public static Date offsetDate (Date date,int year, int month, int day, int hour, int minute, int second){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
        return calendar.getTime();
    }

    private static Date correctDate (String dateStr, String format){
        Map<String, Locale> set = new HashMap<String, Locale>();
        set.put(US_MMM_dd_yyyy_hhmmssSSSaa, Locale.US);
        set.put(yyyyMMdd_HHmmssSSS, Locale.US);
        set.put(US_EEE_MMM_dd_hhmmsszyyyy, Locale.US);
        set.put(yyyyMMdd_HHmmssSSS, Locale.getDefault());
        set.put(yyyy_MM_dd_HHmmss_SSS, Locale.getDefault());
        set.remove(format);
        for (Entry<String, Locale> es : set.entrySet()) {
            SimpleDateFormat sdf = new SimpleDateFormat(es.getKey(),
                    es.getValue());

            try {
                Date s_date = sdf.parse(dateStr);
                return s_date;
            } catch (ParseException e) {
            }
        }
        return null;
    }

    public static Date julianToDate (String julianDate){
        if (julianDate != null && julianDate.length() == 6) {
            String yearBefore = julianDate.substring(0, 1);
            int yearBeforInt = Integer.parseInt(yearBefore) + 19;
            String yearAfter = julianDate.substring(1, 3);
            String year = yearBeforInt + yearAfter;
            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.DAY_OF_YEAR, Integer.parseInt(julianDate.substring(3)));
            return cal.getTime();
        } else {
            return null;
        }
    }
}



