package com.jnj.pangea.omp.certainity.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsCertKeyEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.certainity.service.OMPCertainityServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPCertainityController extends CommonController {

    private ICommonService service = OMPCertainityServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsCertKeyEntity.class), null);
    }
}
