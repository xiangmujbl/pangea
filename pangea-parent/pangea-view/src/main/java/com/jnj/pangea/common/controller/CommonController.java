package com.jnj.pangea.common.controller;

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
            if (null != resultObject){
                if (resultObject.isSuccess()) {
                    BaseBo baseBo = resultObject.getBaseBo();
                    result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
                } else {
                    if (null != resultObject.getFailData()) {
                        FailData failData = resultObject.getFailData();
                        result.add(ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap()));
                    }
                }
            }
        });
        return result;
    }

    public abstract ResultObject process(RawDataEvent raw);
}
