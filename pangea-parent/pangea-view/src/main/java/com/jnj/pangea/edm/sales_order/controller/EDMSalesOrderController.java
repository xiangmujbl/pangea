package com.jnj.pangea.edm.sales_order.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.project_one.VbakEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.sales_order.service.EDMSalesOrderServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class EDMSalesOrderController extends BaseController {

    private EDMSalesOrderServiceImpl service = EDMSalesOrderServiceImpl.getInstance();

    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();

            VbakEntity vbakEntity = BeanUtil.mapToBean(rawValue.toMap(), VbakEntity.class);

            List<ResultObject> resultObjectList = service.buildView(raw.getKey(), vbakEntity, null);

            for (ResultObject resultObject : resultObjectList) {
                if (resultObject.isSuccess()) {
                    BaseBo baseBo = resultObject.getBaseBo();
                    ViewResultItem viewRaw = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
                    result.add(viewRaw);
                } else {
                    if (resultObject.getFailData() != null) {
                        FailData failData = resultObject.getFailData();
                        ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap());
                        result.add(viewResultItem);
                    }
                }
            }

        });
        return result;
    }
}
