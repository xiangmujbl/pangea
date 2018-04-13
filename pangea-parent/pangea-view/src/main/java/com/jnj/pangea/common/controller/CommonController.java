package com.jnj.pangea.common.controller;

import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.ICommonDao;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

import java.util.LinkedList;
import java.util.List;

public abstract class CommonController extends BaseController {

    private ICommonDao commonDao = CommonDaoImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        events.forEach(raw -> {
            ResultObject resultObject = process(raw);
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                if (null != baseBo) {
                    result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
                }
            } else {
                FailData failData = resultObject.getFailData();
                if (null != failData) {
                    result.add(ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap()));
                }
            }
        });
        return result;
    }

    public abstract ResultObject process(RawDataEvent raw);
}
