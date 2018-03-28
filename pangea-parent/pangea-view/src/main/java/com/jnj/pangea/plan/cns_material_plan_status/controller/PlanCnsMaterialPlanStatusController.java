package com.jnj.pangea.plan.cns_material_plan_status.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.plan.cns_material_plan_status.bo.PlanCnsMaterialPlanStatusBo;
import com.jnj.pangea.plan.cns_material_plan_status.service.PlanCnsMaterialPlanStatusServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.LinkedList;
import java.util.List;

public class PlanCnsMaterialPlanStatusController extends BaseController {

    private PlanCnsMaterialPlanStatusServiceImpl pangeaCnsMaterialPlanStatusService = PlanCnsMaterialPlanStatusServiceImpl.getInstance();

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
                    result.add(ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap()));
                }
            }
        });

        List<PlanCnsMaterialPlanStatusBo> materialPlanStatusBoList = getMaterialInclBo();

        for (PlanCnsMaterialPlanStatusBo materialPlanStatusBo:materialPlanStatusBoList) {
            BaseBo baseBo = materialPlanStatusBo;
            result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
        }
        return result;
    }

    public ResultObject process(RawDataEvent raw) {
        return pangeaCnsMaterialPlanStatusService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialPlantV1Entity.class), null);
    }

    public List<PlanCnsMaterialPlanStatusBo> getMaterialInclBo() {
        return pangeaCnsMaterialPlanStatusService.getMaterialInclBo();
    }
}