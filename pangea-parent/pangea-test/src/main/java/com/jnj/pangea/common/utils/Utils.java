package com.jnj.pangea.common.utils;

import com.jnj.adf.client.api.IBiz;
import com.jnj.adf.client.api.IRemoteService;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.support.IGridService;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.*;

public class Utils {

    public Utils() {
    }

    public static String maskPassword(String password) {
        int lengthOfPassword = password.length();
        StringBuilder stringBuilder = new StringBuilder(lengthOfPassword);

        for (int i = 0; i < lengthOfPassword; ++i) {
            stringBuilder.append('*');
        }

        return stringBuilder.toString();
    }

    public static Map<String, String> parseList(List<List<String>> list, String[] keyFields) {
        Assert.assertNotNull(list);
        Assert.assertNotNull(keyFields);
        Assert.assertTrue(keyFields.length > 0);
        LinkedHashSet<String> columnSet = new LinkedHashSet();
        List<String> columns = (List) list.get(0);

        int i;
        for (i = 0; i < columns.size(); ++i) {
            columnSet.add(columns.get(i));
        }

        for (i = 0; i < keyFields.length; ++i) {
            Assert.assertTrue(columnSet.contains(keyFields[i]));
        }

        Map<String, String> map = new HashMap();

        for (i = 1; i < list.size(); ++i) {
            List<String> strs = (List) list.get(i);
            JsonObject value = JsonObject.create();
            Iterator<String> it = columnSet.iterator();

            int j;
            for (j = 0; it.hasNext(); ++j) {
                value.append((String) it.next(), (String) strs.get(j));
            }

            JsonObject key = JsonObject.create();

            for (j = 0; j < keyFields.length; ++j) {
                String columnName = keyFields[j];
                key.append(columnName, StringUtils.trim(value.getValue(columnName)));
            }

            map.put(key.toJson(), value.toJson());
        }

        return map;
    }

    public static Map<String, String> parseListNoKeyInValue(List<List<String>> list, String[] keyFields) {
        return parseList(list, keyFields, (String) null, (String) null);
    }

    public static Map<String, String> parseList(List<List<String>> list, String[] keyFields, String dynamicKeyField, String holdValue) {
        Assert.assertNotNull(list);
        Assert.assertNotNull(keyFields);
        Assert.assertTrue(keyFields.length > 0);
        LinkedHashSet<String> columnSet = new LinkedHashSet();
        List<String> columns = (List) list.get(0);

        for (int i = 0; i < columns.size(); ++i) {
            columnSet.add(columns.get(i));
        }

        SortedSet<String> keySet = new TreeSet();

        for (int i = 0; i < keyFields.length; ++i) {
            Assert.assertTrue(columnSet.contains(keyFields[i]));
            keySet.add(keyFields[i]);
        }

        Map<String, String> map = new HashMap();

        for (int i = 1; i < list.size(); ++i) {
            List<String> strs = (List) list.get(i);
            JsonObject fullValue = JsonObject.create();
            JsonObject value = JsonObject.create();
            Iterator<String> it = columnSet.iterator();

            for (int j = 0; it.hasNext(); ++j) {
                String columnName = (String) it.next();
                if (!keySet.contains(columnName)) {
                    value.append(columnName, (String) strs.get(j));
                }

                fullValue.append(columnName, (String) strs.get(j));
            }

            JsonObject key = JsonObject.create();
            keySet.forEach((columnNamex) -> {
                if (StringUtils.equals(dynamicKeyField, columnNamex)) {
                    key.append(columnNamex, holdValue);
                } else {
                    key.append(columnNamex, StringUtils.trim(fullValue.getValue(columnNamex)));
                }

            });
            map.put(key.toJson(), value.toJson());
        }

        return map;
    }

    public static String encrypt(String text) {
        return DESEncryptor.encrypt(text);
    }

    public static String decrypt(String encryptedText) {
        return DESEncryptor.decrypt(encryptedText);
    }

    public static void waitAqComplete(String fullPath) {
        IRemoteService<IGridService> srv = IBiz.remote(IGridService.class);
        long start = System.currentTimeMillis();
        int total = 0;

        int num;
        do {
            num = ((IGridService) srv.onRegion(fullPath)).getQueueSize(fullPath);
            total += num;
            if (num > 0) {
                LogUtil.getCoreLog().info("Wati aq flush, size:{} ", new Object[]{num});
                Util.sleep(200L);
            }
        } while (num > 0);

        LogUtil.getCoreLog().info("AQ flushed. totalSize:{} cost:{}", new Object[]{total, System.currentTimeMillis() - start});
        Util.sleep(2000L);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("123456"));
    }
}
