package com.jnj.pangea.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseBo {

    private static Map<String, Field[]> fieldCache = new HashMap<>();

    public Map<String, Object> toMap() {

        Map<String, Object> map = new HashMap<>();
        Class clazz = this.getClass();

        Field[] fields = fieldCache.get(clazz.getName());
        if (null == fields) {
            fields = clazz.getDeclaredFields();
            fieldCache.put(clazz.getName(), fields);
        }

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                map.put(field.getName(), field.get(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public abstract String getKey();
}
