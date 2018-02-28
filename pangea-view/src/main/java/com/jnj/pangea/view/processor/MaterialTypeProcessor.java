package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialTypeProcessor extends BaseProcessor implements IEventProcessor {

    private static String SOURCESYSTEM="[EMS]";
    @PostConstruct
    private void initData() {
    }

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();
            Map<String, Object> resultMap = new HashMap<>();
            String sourceSystem= getStringField(rawDataMap, "zSourceSystem");
            // z_source_system = [EMS]
            if (SOURCESYSTEM.equals(sourceSystem)) {
                String key = RawDataHelper.getInstance().makeJsonObject("materialType", getStringField(rawDataMap, "mdmCode"))
                        .toJsonString();
                resultMap.put("materialType", getStringField(rawDataMap, "mdmCode"));
                resultMap.put("materialTypeName", getStringField(rawDataMap, "mdmName"));
                ViewResultItem viewRaw = ViewResultBuilder.newResultItem(key, resultMap);
                result.add(viewRaw);
            }
        });
        return result;
    }


}