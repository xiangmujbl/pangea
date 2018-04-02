package com.jnj.pangea.plan.cns_lot_size_key_trans.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.T439Entity;
import com.jnj.pangea.plan.cns_lot_size_key_trans.service.PlanCnsLotSizeKeyTransServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class PlanCnsLotSizeKeyTransController extends CommonController{

    PlanCnsLotSizeKeyTransServiceImpl cnsLotSizeKeyService = PlanCnsLotSizeKeyTransServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return cnsLotSizeKeyService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), T439Entity.class), null);

    }
}
