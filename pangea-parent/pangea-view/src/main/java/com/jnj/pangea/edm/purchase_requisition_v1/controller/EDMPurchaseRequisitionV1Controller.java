package com.jnj.pangea.edm.purchase_requisition_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.EbanEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.purchase_requisition_v1.service.EDMPurchaseRequisitionV1ServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMPurchaseRequisitionV1Controller extends CommonController {

    private ICommonService service = EDMPurchaseRequisitionV1ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EbanEntity.class), null);
    }
}
