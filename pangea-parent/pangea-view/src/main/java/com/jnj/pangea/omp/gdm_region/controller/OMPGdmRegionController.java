package com.jnj.pangea.omp.gdm_region.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanRegionEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_region.service.OMPGdmRegionServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmRegionController extends CommonController {

    private ICommonService service = OMPGdmRegionServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsPlanRegionEntity.class), null);
    }
}
