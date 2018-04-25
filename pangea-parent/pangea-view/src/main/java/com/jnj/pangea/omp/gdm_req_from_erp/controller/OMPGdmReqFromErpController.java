package com.jnj.pangea.omp.gdm_req_from_erp.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMPurchaseRequisitionV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_req_from_erp.service.OMPGdmReqFromErpServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmReqFromErpController extends CommonController {

    private ICommonService service = OMPGdmReqFromErpServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMPurchaseRequisitionV1Entity.class), null);
    }
}
