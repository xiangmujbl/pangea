package com.jnj.pangea.edm.mfg_order_oper.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectOne.ProjectOneAfvvEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order_oper.service.EDMMfgOrderOperServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgOrderOperController extends CommonController {

    private ICommonService service = EDMMfgOrderOperServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneAfvvEntity.class), null);
    }
}
