package com.jnj.pangea.plan.cns_plng_strat_grp.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.T461PEntity;
import com.jnj.pangea.plan.cns_plng_strat_grp.service.PlanCnsPlngStratGrpServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class PlanCnsPlngStratGrpController extends CommonController {

    PlanCnsPlngStratGrpServiceImpl cnsPlngStratGrpService = PlanCnsPlngStratGrpServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return cnsPlngStratGrpService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), T461PEntity.class), null);

    }
}
