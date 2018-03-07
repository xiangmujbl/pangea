package com.jnj.pangea.common;

import com.jnj.adf.curation.logic.IEventProcessor;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public abstract class BaseController implements IEventProcessor {

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
