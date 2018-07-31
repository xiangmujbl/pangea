package com.jnj.pangea.edm.matl_mfg_allc.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.PlmzEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.matl_mfg_allc.service.EDMMatlMfgAllcServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMatlMfgAllcController extends CommonController {

    private ICommonService service = EDMMatlMfgAllcServiceImpl.getInstance();
    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlmzEntity.class), null);
    }
}
