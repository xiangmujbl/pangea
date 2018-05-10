package com.jnj.pangea.edm.purchase_order_oa_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.EkpoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.purchase_order_oa_v1.service.EDMPurchaseOrderOAServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMPurchaseOrderOAController extends CommonController {

    private ICommonService service = EDMPurchaseOrderOAServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EkpoEntity.class), null);
    }
}
