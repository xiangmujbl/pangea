package com.jnj.pangea.view.processor;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

public abstract class BaseProcessor {

    protected String getStringField(Map<String, Object> rawDataMap, String key) {
        if (null == rawDataMap) {
            return "";
        }
        if (rawDataMap.containsKey(key)) {
            return StringUtils.trim((String) rawDataMap.get(key)) ;
        }
        return "";
    }
}
