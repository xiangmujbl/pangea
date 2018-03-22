package com.jnj.pangea.plan.cns_plng_strat_grp.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectone.T461PEntity;
import com.jnj.pangea.plan.cns_plng_strat_grp.service.CnsPlngStratGrpServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class CnsPlngStratGrpController extends CommonController {

    CnsPlngStratGrpServiceImpl cnsPlngStratGrpService = CnsPlngStratGrpServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return cnsPlngStratGrpService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), T461PEntity.class), null);

    }
}
