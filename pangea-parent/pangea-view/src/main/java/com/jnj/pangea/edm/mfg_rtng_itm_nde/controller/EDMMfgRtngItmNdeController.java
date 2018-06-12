package com.jnj.pangea.edm.mfg_rtng_itm_nde.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlasEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_itm_nde.service.EDMMfgRtngItmNdeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgRtngItmNdeController extends CommonController {

    private ICommonService service = EDMMfgRtngItmNdeServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOnePlasEntity.class), null);
    }
}
