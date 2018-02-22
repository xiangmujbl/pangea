package com.jnj.pangea.view.processor.common;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.processor.common.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sample implements IEventProcessor {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();

            String refRegion = "/sample/info";
            String queryString = "select * from " + refRegion + " where userId = " + rawDataMap.get("id");
            Map.Entry<String, Map<String, Object>> refRaw = AdfViewHelper.queryForMap(refRegion, queryString);

            String key = Utils.generateKey(rawDataMap, "id");
            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(key, rawDataMap);
            result.add(viewRaw);
        });
        return result;
    }
}
