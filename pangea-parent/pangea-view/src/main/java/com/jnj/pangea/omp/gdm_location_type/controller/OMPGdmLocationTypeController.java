package com.jnj.pangea.omp.gdm_location_type.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsLocTypeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_location_type.service.OMPGdmLocationTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmLocationTypeController extends CommonController {

    private ICommonService service = OMPGdmLocationTypeServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsLocTypeEntity.class), null);
    }
}
