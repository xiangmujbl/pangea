package com.jnj.pangea.omp.gdm_resource.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMCapyHdrEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_resource.service.OMPGdmResourceServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmResourceController extends CommonController {

    private ICommonService service = OMPGdmResourceServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMCapyHdrEntity.class), null);
    }
}
