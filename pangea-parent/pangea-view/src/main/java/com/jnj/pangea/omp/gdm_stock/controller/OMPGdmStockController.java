package com.jnj.pangea.omp.gdm_stock.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.edm.EDMAdvancedShipNotificationV1Entity;
import com.jnj.pangea.omp.gdm_stock.service.OMPGdmStockServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class OMPGdmStockController extends BaseController {

    private OMPGdmStockServiceImpl stockService = OMPGdmStockServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();

            //EDMAdvancedShipNotificationV1Entity entity =



        });
        return result;
    }
}
