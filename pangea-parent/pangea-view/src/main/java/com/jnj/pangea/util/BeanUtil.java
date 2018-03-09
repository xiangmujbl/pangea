package com.jnj.pangea.util;

import com.alibaba.fastjson.JSON;
import com.jnj.adf.grid.utils.LogUtil;
import net.sf.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public class BeanUtil {

    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static <T> Object mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null)
            return null;

        Object obj = null;
        try {
            obj = beanClass.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }

                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            LogUtil.getCoreLog().error(">>>map to object InstantiationException info:{}", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LogUtil.getCoreLog().error(">>>map to object IllegalAccessException info:{}", e);
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = null;
        try {
            map = new HashMap<String, Object>();

            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LogUtil.getCoreLog().error(">>>object to map IllegalAccessException info:{}", e);
        }

        return map;
    }
}
