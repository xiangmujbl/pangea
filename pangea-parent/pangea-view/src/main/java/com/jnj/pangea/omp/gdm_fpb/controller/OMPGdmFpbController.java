package com.jnj.pangea.omp.gdm_fpb.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_fpb.service.OMPGdmFpbServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmFpbController extends CommonController {

    private ICommonService service = OMPGdmFpbServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialGlobalV1Entity.class), null);
    }
}
