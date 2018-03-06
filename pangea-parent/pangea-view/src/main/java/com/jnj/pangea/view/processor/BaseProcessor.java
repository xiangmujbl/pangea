package com.jnj.pangea.view.processor;

import java.util.Map;

public abstract class BaseProcessor {

    protected String getStringField(Map<String, Object> rawDataMap, String key) {
        if (null == rawDataMap) {
            return "";
        }
        return rawDataMap.get(key) + "";
    }
}
