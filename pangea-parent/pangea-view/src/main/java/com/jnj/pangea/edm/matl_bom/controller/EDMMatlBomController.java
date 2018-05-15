package com.jnj.pangea.edm.matl_bom.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectOne.ProjectOneMastEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.matl_bom.service.EDMMatlBomServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMatlBomController extends CommonController {

    private ICommonService service = EDMMatlBomServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneMastEntity.class), null);
    }
}
