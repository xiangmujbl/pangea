package com.jnj.pangea.common;

import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class CommonController implements IEventProcessor {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        events.forEach(raw -> {
            ResultObject resultObject = process(raw.getValue().toMap());
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();

                LogUtil.getCoreLog().info("---------------{}", baseBo.toMap());
                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
            } else {
                FailData failData = resultObject.getFailData();
                // TODO write failed record
            }
        });
        return result;
    }

    public abstract ResultObject process(Map<String, Object> rawMap);
}
