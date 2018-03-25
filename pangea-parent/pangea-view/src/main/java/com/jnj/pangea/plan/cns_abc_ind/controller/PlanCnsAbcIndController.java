package com.jnj.pangea.plan.cns_abc_ind.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.TmabcEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_abc_ind.service.PlanCnsAbcIndServiceImpl;
import com.jnj.pangea.util.BeanUtil;


public class PlanCnsAbcIndController extends CommonController {

    private ICommonService planCnsAbcService = PlanCnsAbcIndServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return planCnsAbcService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), TmabcEntity.class),null);
    }
}
