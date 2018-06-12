package com.jnj.pangea.edm.mfg_order_seq.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectOne.ProjectOneAfflEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order_seq.service.EDMMfgOrderSeqServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgOrderSeqController extends CommonController {

    private ICommonService service = EDMMfgOrderSeqServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneAfflEntity.class), null);
    }
}
