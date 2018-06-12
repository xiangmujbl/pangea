package com.jnj.pangea.edm.capy_hdr.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectOne.ProjectOneKakoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.capy_hdr.service.EDMCapyHdrServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMCapyHdrController extends CommonController {

    private ICommonService service = EDMCapyHdrServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneKakoEntity.class), null);
    }
}
