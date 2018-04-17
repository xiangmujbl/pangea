package com.jnj.pangea.plan.cns_material_plan_status.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.plan.cns_material_plan_status.service.PlanCnsMaterialPlanStatusServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class PlanCnsMaterialPlanStatusController extends CommonController {

    private PlanCnsMaterialPlanStatusServiceImpl pangeaCnsMaterialPlanStatusService = PlanCnsMaterialPlanStatusServiceImpl.getInstance();

    public ResultObject process(RawDataEvent raw) {
        return pangeaCnsMaterialPlanStatusService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialPlantV1Entity.class), null);
    }

}