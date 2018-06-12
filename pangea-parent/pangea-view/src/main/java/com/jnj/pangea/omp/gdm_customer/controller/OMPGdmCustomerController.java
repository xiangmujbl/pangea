package com.jnj.pangea.omp.gdm_customer.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanDemGrpEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_customer.service.OMPGdmCustomerServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmCustomerController extends CommonController {

    private ICommonService service = OMPGdmCustomerServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsPlanDemGrpEntity.class), null);
    }
}
