package com.jnj.pangea.plan.cns_spl_proc_typ.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.T460AEntity;
import com.jnj.pangea.plan.cns_spl_proc_typ.service.PlanCnsSplProcTypServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class PlanCnsSplProcTypController extends CommonController{

    private PlanCnsSplProcTypServiceImpl procTypService=PlanCnsSplProcTypServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return procTypService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(),T460AEntity.class),null);
    }
}
