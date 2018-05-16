package com.jnj.pangea.omp.location_type.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsLocationTypeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.location_type.service.OMPLocationTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPLocationTypeController extends CommonController {

    private ICommonService service = OMPLocationTypeServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsLocationTypeEntity.class), null);
    }
}
