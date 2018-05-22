package com.jnj.pangea.edm.mfg_rtng_hdr.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.PlkoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_hdr.service.EDMMfgRtngHdrServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgRtngHdrController extends CommonController {

    private ICommonService service = EDMMfgRtngHdrServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlkoEntity.class), null);
    }
}
