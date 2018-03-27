package com.jnj.pangea.edm.sales_order.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.VbakEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.sales_order.service.EDMSalesOrderServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMSalesOrderController extends CommonController {

    private ICommonService service = EDMSalesOrderServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), VbakEntity.class), null);
    }
}
