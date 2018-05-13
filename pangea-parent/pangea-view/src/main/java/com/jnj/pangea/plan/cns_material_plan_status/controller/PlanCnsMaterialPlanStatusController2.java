package com.jnj.pangea.plan.cns_material_plan_status.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.plan.cns_material_plan_status.service.PlanCnsMaterialPlanStatusServiceImpl2;
import com.jnj.pangea.util.BeanUtil;

import java.util.*;


public class PlanCnsMaterialPlanStatusController2 extends BaseController {

    private PlanCnsMaterialPlanStatusServiceImpl2 cnsMaterialPlanStatusService = PlanCnsMaterialPlanStatusServiceImpl2.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            CnsMaterialInclEntity materialInclEntity = BeanUtil.mapToBean(rawValue.toMap(), CnsMaterialInclEntity.class);
            ResultObject resultObject = cnsMaterialPlanStatusService.buildView(raw.getKey(), materialInclEntity);
            if(resultObject == null){
                return;
            }
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
            } else {
                if (null != resultObject.getFailData()) {
                    FailData failData = resultObject.getFailData();
                    result.add(ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap()));
                }
            }
        });

        return result;
    }
}