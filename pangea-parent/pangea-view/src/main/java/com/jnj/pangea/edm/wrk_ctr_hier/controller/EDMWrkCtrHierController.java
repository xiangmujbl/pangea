package com.jnj.pangea.edm.wrk_ctr_hier.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.CrhsEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.wrk_ctr_hier.service.EDMWrkCtrHierServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMWrkCtrHierController extends CommonController {

    private ICommonService service = EDMWrkCtrHierServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), CrhsEntity.class), null);
    }
}
