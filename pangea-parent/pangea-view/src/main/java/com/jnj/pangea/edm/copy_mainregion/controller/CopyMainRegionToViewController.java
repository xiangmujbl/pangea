package com.jnj.pangea.edm.copy_mainregion.controller;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;

import java.util.*;
import java.util.Map.Entry;

public class CopyMainRegionToViewController  implements IEventProcessor {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new LinkedList<>();
        list.forEach(raw -> {
            Map resultMap =new HashMap();
            Map map = raw.getValue().toMap();
            Set<Entry<String, String>> entries = map.entrySet();
            String key=raw.getKey();
            for(Entry<String, String> entry: entries) {
                String value=entry.getValue();
                resultMap.put(entry.getKey(),value);
                result.add(ViewResultBuilder.newResultItem(key, resultMap));
            }

            });
        return result;
    }
}
