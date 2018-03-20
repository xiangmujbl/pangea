package com.jnj.pangea.util;

import java.util.Map;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public class BeanUtil {

//    private static Map<String, Field[]> fieldCache = new HashMap<>();

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.getDeclaredConstructor(Map.class).newInstance(map);
//            instance = clazz.newInstance();
//            Field[] fields = fieldCache.get(clazz.getName());
//            if (null == fields) {
//                fields = clazz.getDeclaredFields();
//                fieldCache.put(clazz.getName(), fields);
//            }
//            for (Field field : fields) {
//                int mod = field.getModifiers();
//                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
//                    continue;
//                }
//                field.setAccessible(true);
//                field.set(instance, map.get(field.getName()));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
