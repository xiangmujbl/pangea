package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.view.bo.EDMSourceSystemBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EDMSourceSystemProcessor extends BaseProcessor implements IEventProcessor {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();

            EDMSourceSystemBo edmSourceSystemBo = new EDMSourceSystemBo();

            edmSourceSystemBo.setLocalSourceSystem(getStringField(rawDataMap, "localSourceSystem"));
            edmSourceSystemBo.setLocalSourceSystemName(getStringField(rawDataMap, "localSourceSystemName"));
            edmSourceSystemBo.setSourceSystem(getStringField(rawDataMap, "sourceSystem"));
            edmSourceSystemBo.setSourceSystemName(getStringField(rawDataMap, "sourceSystemName"));

            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(edmSourceSystemBo.getKey(), edmSourceSystemBo.toMap());
            result.add(viewRaw);
        });
        return result;
    }
}