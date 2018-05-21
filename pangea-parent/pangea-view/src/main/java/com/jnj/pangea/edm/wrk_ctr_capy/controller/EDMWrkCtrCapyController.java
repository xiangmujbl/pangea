package com.jnj.pangea.edm.wrk_ctr_capy.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectOne.ProjectOneCrcaEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.wrk_ctr_capy.service.EDMWrkCtrCapyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMWrkCtrCapyController extends CommonController {

    private ICommonService service = EDMWrkCtrCapyServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneCrcaEntity.class), null);
    }
}
