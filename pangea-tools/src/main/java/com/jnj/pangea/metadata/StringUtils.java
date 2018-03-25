package com.jnj.pangea.metadata;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str) || str.length() == 0;
    }

    public static String capFirst(String param) {
        return param.substring(0, 1).toUpperCase() + param.substring(1, param.length());
    }

    public static String transformToCamelCase(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == '_') {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
