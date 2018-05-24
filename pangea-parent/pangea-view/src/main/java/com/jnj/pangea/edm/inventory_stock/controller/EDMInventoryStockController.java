package com.jnj.pangea.edm.inventory_stock.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.project_one.MarcEntity;
import com.jnj.pangea.edm.inventory_stock.service.EDMInventoryStockServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class EDMInventoryStockController extends BaseController {

    private EDMInventoryStockServiceImpl service = EDMInventoryStockServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();
            MarcEntity projectOneMarcEntity = BeanUtil.mapToBean(rawValue.toMap(), MarcEntity.class);

            List<ResultObject> resultObjectList = service.buildView(raw.getKey(),projectOneMarcEntity , null);
            for (ResultObject resultObject:resultObjectList) {
                if (resultObject.isSuccess()) {
                    BaseBo baseBo = resultObject.getBaseBo();
                    ViewResultItem viewRaw = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
                    result.add(viewRaw);
                } else {
                    if (resultObject.getFailData() != null) {
                        FailData failData = resultObject.getFailData();
                        ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap());
                        result.add(viewResultItem);
                    }
                }
            }
        });
        return result;

    }
}
