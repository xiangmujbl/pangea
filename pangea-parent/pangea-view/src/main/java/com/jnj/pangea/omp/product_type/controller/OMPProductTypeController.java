package com.jnj.pangea.omp.product_type.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialTypeV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.product_type.service.OMPProductTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPProductTypeController extends CommonController {

    private ICommonService service = OMPProductTypeServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialTypeV1Entity.class), null);
    }
}
