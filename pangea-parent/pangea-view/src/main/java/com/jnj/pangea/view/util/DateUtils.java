package com.jnj.pangea.view.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by XZhan290 on 2018/3/2.
 */
public class DateUtils {
    public final static String DATE_FORMAT_1 = "yyyy-MM-dd";
    public final static String F_yyyyMMdd = "yyyyMMdd";
    public final static String F_yyyyMM = "yyyyMM";
    public final static String F_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public final static String yyyyMMdd_HHmmss = "yyyyMMdd_HH-mm-ss";
    public final static String yyyy_MM_dd_HHmmss_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
    public final static String yyyyMMdd_HHmmssSSS = "yyyyMMdd HH:mm:ss:SSS";
    public final static String yyyy_MM_dd_HHmmssSSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String US_MMM_dd_yyyy_hhmmssSSSaa = "MMM dd yyyy hh:mm:ss:SSSaa";
    public final static String US_EEE_MMM_dd_hhmmsszyyyy = "EEE MMM dd HH:mm:ss z yyyy";
    public final static String dd_MM_yyyy_HHmmss = "dd/MM/yyyy HH:mm:ss";

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
}
