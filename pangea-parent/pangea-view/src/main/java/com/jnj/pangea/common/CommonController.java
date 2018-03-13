package com.jnj.pangea.common;

import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.dao.ICommonDao;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

import java.util.LinkedList;
import java.util.List;

public abstract class CommonController implements IEventProcessor {

    private ICommonDao commonDao = CommonDaoImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        events.forEach(raw -> {
            ResultObject resultObject = process(raw);
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
            } else {
                if (null != resultObject.getFailData()) {
                    FailData failData = resultObject.getFailData();
                    result.add(ViewResultBuilder.newResultItem(CommonRegionPath.FAIL_DATA, failData.getKey(), failData.toMap()));
                }
            }
        });
        return result;
    }

    public abstract ResultObject process(RawDataEvent raw);
}
