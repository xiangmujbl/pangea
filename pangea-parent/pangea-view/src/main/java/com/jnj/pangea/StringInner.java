package com.jnj.pangea;


import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class StringInner {

    public static boolean isStringNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    public static boolean isStringEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isObjectNull(Object obj) {
        return obj == null;
    }

    public static boolean isMapNull(Map map) {
        return map == null;
    }

    public static boolean isMapNullWithSize(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isMapNotNullWithSize(Map map) {
        return map != null && map.size() != 0;
    }

    //list
    public static boolean isListNull(List list) {
        return list == null;
    }

    public static boolean isListNullWithSize(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isListNotNullWithSize(List list) {
        return list != null && list.size() != 0;
    }

    //set
    public static boolean isSetNull(Set set) {
        return set == null;
    }

    public static boolean isSetNullWithSize(Set set) {
        return set == null || set.size() == 0;
    }

    public static boolean isSetNotNullWithSize(Set set) {
        return set != null && set.size() != 0;
    }

    public static String join(String... str) {
        return StringUtils.join(str);
    }

    public static String subString(String str, int beginIndex, int endIndex) {
        if (isStringEmpty(str)) return str;
        return str.substring(beginIndex, endIndex);
    }

    public static String[] split(String str, String separator) {
        if (isStringEmpty(str)) return null;
        return str.split(separator);
    }

    public static boolean equal(String str1, String str2) {
        if(str1 == null && str2 == null) return true;
        else if(str1 == null && str2 !=null) return false;
        else return str1.equals(str2);
    }

    public static String trim(String s){
        return s==null?s:s.trim();
    }

    public static String getString(Map<String, Object> m, String field) {
        if (m != null) {
            Object o = m.get(field);
            return o != null ? o.toString() : null;
        }
        return null;
    }

}