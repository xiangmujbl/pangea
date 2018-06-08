package com.jnj.pangea.edm.material_global.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.MaraEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material_global.service.EDMMaterialGlobalServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMaterialGlobalController extends CommonController {

    private ICommonService service = EDMMaterialGlobalServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), MaraEntity.class), null);
    }
}
