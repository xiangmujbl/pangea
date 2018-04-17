package com.jnj.pangea.omp.product_detail.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.product_detail.service.OMPProductDetailServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPProductDetailController extends CommonController {

    private OMPProductDetailServiceImpl service = OMPProductDetailServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialGlobalV1Entity.class), null);
    }
}
