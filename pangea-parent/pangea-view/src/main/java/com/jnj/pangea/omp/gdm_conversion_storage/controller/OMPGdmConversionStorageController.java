package com.jnj.pangea.omp.gdm_conversion_storage.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPriceEntity;
import com.jnj.pangea.omp.gdm_conversion_storage.service.OMPGdmConversionStorageServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.LinkedList;
import java.util.List;

public class OMPGdmConversionStorageController extends BaseController {

    private OMPGdmConversionStorageServiceImpl service = OMPGdmConversionStorageServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        events.forEach(raw -> {
            List<ResultObject> resultObjects = process(raw);
            resultObjects.forEach(resultObject -> {
                if (resultObject.isSuccess()) {
                    BaseBo baseBo = resultObject.getBaseBo();
                    result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
                } else {
                    if (null != resultObject.getFailData()) {
                        FailData failData = resultObject.getFailData();
                        result.add(ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap()));
                    }
                }
            });
        });
        return result;
    }

    private List<ResultObject> process(RawDataEvent raw) {

        List<ResultObject> resultObjects = new LinkedList<>();

        PlanCnsDpPriceEntity cnsDpPriceEntity = BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsDpPriceEntity.class);
        List<String> aggIds = service.getFieldWithC1(cnsDpPriceEntity.getLocalMaterialNumber(), cnsDpPriceEntity.getCountry());

        if (!aggIds.isEmpty()) {
            aggIds.forEach(aggId -> resultObjects.add(service.buildView(raw.getKey(), cnsDpPriceEntity, aggId)));
        }
        return resultObjects;
    }
}
