package com.jnj.pangea.view.bo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseBo {

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        Class classes = this.getClass();
        Field[] fields = classes.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(this));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
