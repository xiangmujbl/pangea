package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.view.processor.common.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaterialGlobal implements IEventProcessor {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();
            String key = Utils.generateKey(rawDataMap, "sourceSystem", "localMaterialNumber");
            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(key, rawDataMap);
            result.add(viewRaw);
        });
        return result;
    }
}