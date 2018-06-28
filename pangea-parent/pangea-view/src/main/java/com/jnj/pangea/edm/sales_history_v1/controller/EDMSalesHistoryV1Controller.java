package com.jnj.pangea.edm.sales_history_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.ProjectOneVbfaEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.sales_history_v1.service.EDMSalesHistoryV1ServiceImpl;
import com.jnj.pangea.util.BeanUtil;

/**
 * @Name: EDMSalesHistoryV1Controller
 * @Description: controller for sales_history_v1 of edm system
 * @author KG(Kelvin Gu)   
 * @date 06-12-2018 03:19:08 
*/
public class EDMSalesHistoryV1Controller extends CommonController {

    private ICommonService edmSales_historyService = EDMSalesHistoryV1ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return edmSales_historyService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneVbfaEntity.class), null);
    }

}

