package com.jnj.pangea.omp.gdm_bom.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderEntity;
import com.jnj.pangea.omp.gdm_bom.service.OMPGdmBomServiceImpl2;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class OMPGdmBomController2 extends BaseController {

    private OMPGdmBomServiceImpl2 service = OMPGdmBomServiceImpl2.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();
            EDMMfgOrderEntity edmMfgOrderEntity = BeanUtil.mapToBean(rawValue.toMap(), EDMMfgOrderEntity.class);

            List<ResultObject> resultObjectList = service.buildView(raw.getKey(),edmMfgOrderEntity , null);
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
