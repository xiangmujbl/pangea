package com.jnj.pangea.plan.cns_plant_attr.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_plant_attr.bo.PlanCnsPlantAttrBo;

public class PlanCnsPlantAttrServiceImpl implements ICommonService {

    private static PlanCnsPlantAttrServiceImpl instance;

    public static PlanCnsPlantAttrServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlantAttrServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMPlantV1Entity plantV1Entity = (EDMPlantV1Entity) o;

//        if (IConstant.VALUE.Y.equals(plantV1Entity.getLocalPlanningRelevant())) {
        PlanCnsPlantAttrBo planCnsPlantAttrBo = new PlanCnsPlantAttrBo();
        planCnsPlantAttrBo.setSourceSystem(plantV1Entity.getSourceSystem());
        planCnsPlantAttrBo.setLocalPlant(plantV1Entity.getLocalPlant());
        planCnsPlantAttrBo.setLocalPlantName(plantV1Entity.getLocalPlantName());
        planCnsPlantAttrBo.setLocalPlantType(plantV1Entity.getLocalPlantType());
        planCnsPlantAttrBo.setPlant(plantV1Entity.getPlant());
        planCnsPlantAttrBo.setPlantType(plantV1Entity.getPlantType());
        planCnsPlantAttrBo.setLocalPlanningRelevant(plantV1Entity.getLocalPlanningRelevant());
        resultObject.setBaseBo(planCnsPlantAttrBo);
//        }

        return resultObject;
    }
}
