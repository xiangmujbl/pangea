package com.jnj.pangea.common;

import java.util.Map;

public abstract class BaseController {

    protected String getStringField(Map<String, Object> rawDataMap, String key) {
        if (null == rawDataMap) {
            return "";
        }
        return rawDataMap.get(key) + "";
    }
}
