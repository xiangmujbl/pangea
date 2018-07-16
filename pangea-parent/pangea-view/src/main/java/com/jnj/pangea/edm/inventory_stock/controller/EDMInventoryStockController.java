package com.jnj.pangea.edm.inventory_stock.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.project_one.MardEntity;
import com.jnj.pangea.edm.inventory_stock.service.EDMInventoryStockServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

public class EDMInventoryStockController extends CommonListController {

    private EDMInventoryStockServiceImpl service = EDMInventoryStockServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), MardEntity.class), null);
    }
}
