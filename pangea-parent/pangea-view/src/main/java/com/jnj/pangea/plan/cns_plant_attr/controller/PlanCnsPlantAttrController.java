package com.jnj.pangea.plan.cns_plant_attr.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_plant_attr.service.PlanCnsPlantAttrServiceImpl;
import com.jnj.pangea.util.BeanUtil;


public class PlanCnsPlantAttrController extends CommonController {

    private ICommonService plantAttrService = PlanCnsPlantAttrServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return plantAttrService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMPlantV1Entity.class), null);
    }
}
