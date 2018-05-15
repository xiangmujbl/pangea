package com.jnj.pangea.edm.bom_hdr.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.StkoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.bom_hdr.service.EDMBomHdrServiceImpl;
import com.jnj.pangea.util.BeanUtil;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMBomHdrController extends CommonController {

    private ICommonService edmBomHdrService = EDMBomHdrServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return edmBomHdrService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), StkoEntity.class), null);
    }

}
