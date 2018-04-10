package com.jnj.pangea.omp.process_type.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.process_type.service.OMPProcessTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPProcessTypeController extends CommonController {

    private ICommonService service = OMPProcessTypeServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsProcessTypeEntity.class), null);
    }
}
