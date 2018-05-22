package com.jnj.pangea.edm.wrk_ctr.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.CrhdEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.wrk_ctr.service.EDMWrkCtrServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMWrkCtrController extends CommonController {

    private ICommonService service = EDMWrkCtrServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), CrhdEntity.class), null);
    }
}
