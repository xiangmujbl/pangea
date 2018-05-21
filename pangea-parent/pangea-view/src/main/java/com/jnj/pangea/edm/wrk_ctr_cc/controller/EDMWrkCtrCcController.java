package com.jnj.pangea.edm.wrk_ctr_cc.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectOne.ProjectOneCrcoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.wrk_ctr_cc.service.EDMWrkCtrCcServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMWrkCtrCcController extends CommonController {

    private ICommonService service = EDMWrkCtrCcServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneCrcoEntity.class), null);
    }
}
