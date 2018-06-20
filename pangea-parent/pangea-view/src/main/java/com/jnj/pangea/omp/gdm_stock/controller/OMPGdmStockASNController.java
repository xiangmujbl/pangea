package com.jnj.pangea.omp.gdm_stock.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMAdvancedShipNotificationV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_stock.service.OMPGdmStockASNServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmStockASNController extends CommonController {

    private ICommonService stockService = OMPGdmStockASNServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {

        ResultObject result = stockService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMAdvancedShipNotificationV1Entity.class), null);
        return result;
    }
}
