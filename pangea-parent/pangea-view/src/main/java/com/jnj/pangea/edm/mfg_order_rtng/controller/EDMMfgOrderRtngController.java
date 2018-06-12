package com.jnj.pangea.edm.mfg_order_rtng.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.AfvcEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order_rtng.service.EDMMfgOrderRtngServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgOrderRtngController extends CommonController {

    private ICommonService service = EDMMfgOrderRtngServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), AfvcEntity.class), null);
    }
}
