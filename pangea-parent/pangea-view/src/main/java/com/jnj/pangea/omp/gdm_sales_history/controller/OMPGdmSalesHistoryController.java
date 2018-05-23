package com.jnj.pangea.omp.gdm_sales_history.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_sales_history.service.OMPGdmSalesHistoryServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class OMPGdmSalesHistoryController extends BaseController {

    private ICommonService service = OMPGdmSalesHistoryServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        Set<String> scheduleLineItemSet = new HashSet<>();
        events.forEach(raw -> {
            try {
                ResultObject resultObject = process(raw,scheduleLineItemSet);
                if (null != resultObject){
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
            } catch (Exception e) {
                LogUtil.getCoreLog().info(e);
            }
        });

        return result;
    }

    public ResultObject process(RawDataEvent raw,Set<String> scheduleLineItemSet) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMSalesOrderV1Entity.class), scheduleLineItemSet);
    }
}
