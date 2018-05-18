package com.jnj.pangea.edm.mfg_rtng_seq.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.PlflEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_seq.service.EDMMfgRtngSeqServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgRtngSeqController extends CommonController {

    private ICommonService service = EDMMfgRtngSeqServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        LogUtil.getCoreLog().info("======22222222222222222222222=====" );
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlflEntity.class), null);

    }
}
