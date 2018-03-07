package com.jnj.pangea.common;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;

import java.util.LinkedList;
import java.util.List;

public abstract class CommonController implements IEventProcessor {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        events.forEach(raw -> {
            ResultObject resultObject = process(raw.getValue());
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
            } else {
                FailData failData = resultObject.getFailData();
                // TODO write failed record
            }
        });
        return result;
    }

    public abstract ResultObject process(RawDataValue rawDataValue);
}
