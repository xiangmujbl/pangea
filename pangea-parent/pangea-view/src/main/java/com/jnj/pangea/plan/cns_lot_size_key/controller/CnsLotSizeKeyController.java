package com.jnj.pangea.plan.cns_lot_size_key.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectone.T439Entity;
import com.jnj.pangea.plan.cns_lot_size_key.service.CnsLotSizeKeyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class CnsLotSizeKeyController extends CommonController{

    CnsLotSizeKeyServiceImpl cnsLotSizeKeyService = CnsLotSizeKeyServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return cnsLotSizeKeyService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), T439Entity.class), null);

    }
}
