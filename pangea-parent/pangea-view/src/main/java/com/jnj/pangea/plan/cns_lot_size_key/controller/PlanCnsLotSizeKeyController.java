package com.jnj.pangea.plan.cns_lot_size_key.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.T439Entity;
import com.jnj.pangea.plan.cns_lot_size_key.service.PlanCnsLotSizeKeyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class PlanCnsLotSizeKeyController extends CommonController{

    PlanCnsLotSizeKeyServiceImpl cnsLotSizeKeyService = PlanCnsLotSizeKeyServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return cnsLotSizeKeyService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), T439Entity.class), null);

    }
}
