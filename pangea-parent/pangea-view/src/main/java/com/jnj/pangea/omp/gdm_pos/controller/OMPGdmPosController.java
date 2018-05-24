package com.jnj.pangea.omp.gdm_pos.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPosEntity;
import com.jnj.pangea.omp.gdm_pos.service.OMPGdmPosServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

public class OMPGdmPosController extends CommonListController {

    private OMPGdmPosServiceImpl service = OMPGdmPosServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsDpPosEntity.class), null);
    }
}
