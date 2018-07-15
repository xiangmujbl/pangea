package com.jnj.pangea.edm.mfg_order.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.AfkoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order.service.EDMMfgOrderServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgOrderController extends CommonController {

    private ICommonService service = EDMMfgOrderServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), AfkoEntity.class), null);
    }
}
