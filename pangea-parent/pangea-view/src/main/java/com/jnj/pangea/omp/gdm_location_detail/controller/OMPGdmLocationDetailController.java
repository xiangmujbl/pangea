package com.jnj.pangea.omp.gdm_location_detail.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;
import com.jnj.pangea.omp.gdm_location_detail.service.OMPGdmLocationDetailServiceImpl;
import com.jnj.pangea.util.BeanUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class OMPGdmLocationDetailController extends BaseController {

    private OMPGdmLocationDetailServiceImpl service = OMPGdmLocationDetailServiceImpl.getInstance();

    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();

            PlanCnsPlantAttrEntity plantAttrEntity = BeanUtil.mapToBean(rawValue.toMap(), PlanCnsPlantAttrEntity.class);

            List<ResultObject> resultObjectList = service.buildView(raw.getKey(), plantAttrEntity, null);

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
