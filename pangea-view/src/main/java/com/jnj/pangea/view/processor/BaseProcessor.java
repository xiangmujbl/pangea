package com.jnj.pangea.view.processor;

import java.util.Map;

public abstract class BaseProcessor {

    protected String getStringField(Map<String, Object> rawDataMap, String key) {
        return rawDataMap.get(key) + "";
    }
}
