package com.jnj.pangea.edm.mfg_rtng_rltnshp.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlabEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_rltnshp.service.EDMMfgRtngRltnshpServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgRtngRltnshpController extends CommonController {

    private ICommonService service = EDMMfgRtngRltnshpServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOnePlabEntity.class), null);
    }
}
