package com.jnj.pangea.omp.gdm_stock.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_stock.service.OMPGdmStockPurchaseOrderServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.*;

public class OMPGdmStockPurchaseOrderController extends CommonController {

    private ICommonService service = OMPGdmStockPurchaseOrderServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMPurchaseOrderOAV1Entity.class), null);
    }
}
