package com.jnj.pangea.view.processor.common;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class Utils {

    public static String generateKey(Map<String, Object> raw, String... keys) {
        JSONObject jsonObject = new JSONObject();
        for (String key : keys) {
            jsonObject.put(key, raw.get(key));
        }
        return jsonObject.toJSONString();
    }
}
