package com.jnj.pangea.edm.mfg_rtng_itm.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlpoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_itm.service.EDMMfgRtngItmServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgRtngItmController extends CommonController {

    private ICommonService service = EDMMfgRtngItmServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOnePlpoEntity.class), null);
    }
}
