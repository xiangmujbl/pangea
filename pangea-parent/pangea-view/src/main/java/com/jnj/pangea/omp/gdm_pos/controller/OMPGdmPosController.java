package com.jnj.pangea.omp.gdm_pos.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPosEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_pos.service.OMPGdmPosServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmPosController extends CommonController {

    private ICommonService service = OMPGdmPosServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsDpPosEntity.class), null);
    }
}
