package com.jnj.pangea.hook.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by XZhan290 on 2018/3/2.
 */
public class DateUtils {

    public final static String DATE_FORMAT_1 = "yyyy-MM-dd";
    public final static String F_yyyyMMdd = "yyyyMMdd";
    public final static String F_yyyyMM = "yyyyMM";
    public final static String F_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public final static String F_yyyyMMddHHmmss_ = "yyyyMMddHH:mm:ss";
    public final static String yyyyMMdd_HHmmss = "yyyyMMdd_HH-mm-ss";
    public final static String yyyy_MM_dd_HHmmss_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
    public final static String yyyyMMdd_HHmmssSSS = "yyyyMMdd HH:mm:ss:SSS";
    public final static String yyyy_MM_dd_HHmmssSSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String US_MMM_dd_yyyy_hhmmssSSSaa = "MMM dd yyyy hh:mm:ss:SSSaa";
    public final static String US_EEE_MMM_dd_hhmmsszyyyy = "EEE MMM dd HH:mm:ss z yyyy";
    public final static String dd_MM_yyyy_HHmmss = "dd/MM/yyyy HH:mm:ss";
    public final static String F_dd_MM_yyyy_HHmmss = "dd-MM-yyyy HH:mm:ss";
    public final static String F_yyyy_MM_dd_HHmmss = "yyyyMMdd HH:mm:ss";
    public final static String J_yyyyWW = "yyyyWW";
    public final static String yyyy_MM_dd_HHmmss = "yyyy/dd/MM HH:mm:ss";
    public final static String yyyy_MM_dd_HHmmss_TRUE = "yyyy/MM/dd HH:mm:ss";
    public final static String yyyy_MM_dd = "yyyy/MM/dd";
    public final static String dd_MM_yyyy = "dd/MM/yyyy";
    public final static String yyyy_MM_ddTHHmmss = "yyyy-MM-dd'T'HH:mm:ss";
    public final static String ISO_8601 = "yyyy-mm-ddThh:mm:ss.ffffff";
    public final static String ISO_8602 = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public final static String J_yyyyMMdd_HHmmss = "yyyy/MM/dd HH:mm:ss";
    public final static String J_yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";

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
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(dt);
    }

    public static Date stringToDate(String dateStr, String format) {
        return stringToDate(dateStr, format, Locale.getDefault());
    }

    public static Date stringToDate(String dateStr, String format, Locale locale) {

        if (J_yyyyWW.equals(format) && dateStr.length() == 6) {
            String year = dateStr.substring(0, 4);
            String week = dateStr.substring(4, 6);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(year), Calendar.JANUARY, 0, 0, 0, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(week));
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
            return calendar.getTime();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        Date s_date = null;
        try {
            s_date = (Date) sdf.parse(dateStr);
        } catch (ParseException e) {
            s_date = correctDate(dateStr, format);
        }
        return s_date;
    }

    private static Date correctDate(String dateStr, String format) {
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

    public static Date startOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        return calendar.getTime();
    }

    public static Date endOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }

    public static Date offsetDate(Date date, int days) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
        return calendar.getTime();
    }

    public static String derivationDate(String dateString, int num) {
        int dayForWeek = 1;
        int numWeekend = 2;
        int numWeekday = 5;

        Calendar calendar = Calendar.getInstance();
        Date date = DateUtils.stringToDate(dateString, DateUtils.yyyy_MM_dd);
        if (null != date) {
            calendar.setTime(date);
            dayForWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (dayForWeek == 6) {
                num -= 1;
            }
            int numWeek = (num) / numWeekday;
            if (dayForWeek + (num) % numWeekday > numWeekday) {
                numWeek = numWeek + 1;
            }
            num = num + numWeek * numWeekend;
            calendar.add(Calendar.DATE, num);
            return DateUtils.dateToString(calendar.getTime(), DateUtils.yyyy_MM_dd);
        }
        return "";
    }

    public static Date changeDate(Date date, int year, int month, int day, int hour, int minute, int second) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+year);
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+month);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
        return calendar.getTime();
    }

    public static void main(String[] args) {

        System.out.println(derivationDate("2018/06/07", 1));
    }
}
