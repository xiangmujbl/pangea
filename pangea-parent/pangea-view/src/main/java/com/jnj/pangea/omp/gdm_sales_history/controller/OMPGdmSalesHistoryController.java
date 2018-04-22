package com.jnj.pangea.omp.gdm_sales_history.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_sales_history.service.OMPGdmSalesHistoryServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmSalesHistoryController extends CommonController {

    private ICommonService service = OMPGdmSalesHistoryServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMSalesOrderV1Entity.class), null);
    }
}
