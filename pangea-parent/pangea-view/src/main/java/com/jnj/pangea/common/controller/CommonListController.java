package com.jnj.pangea.common.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.ICommonDao;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

import java.util.LinkedList;
import java.util.List;

public abstract class CommonListController extends BaseController {

    private ICommonDao commonDao = CommonDaoImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        events.forEach(raw -> {
            List<ResultObject> list = process(raw);
            if(list!=null&&list.size()>0){
                for(ResultObject resultObject:list){
                    if (null != resultObject) {
                        if (resultObject.isSuccess()) {
                            BaseBo baseBo = resultObject.getBaseBo();
                            if (null != baseBo) {
                                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
                            }
                        } else {
                            if (null != resultObject.getFailData()) {
                                FailData failData = resultObject.getFailData();
                                result.add(ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap()));
                            }
                        }
                    }
                }

            }

        });
        return result;
    }

    public abstract List<ResultObject> process(RawDataEvent raw);
}
