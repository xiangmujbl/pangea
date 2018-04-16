package com.jnj.pangea.omp.gdm_product_location_detail.controller;

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
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_product_location_detail.service.OMPGdmProductLocationDetailServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class OMPGdmProductLocationDetailController extends BaseController {

    private OMPGdmProductLocationDetailServiceImpl service = OMPGdmProductLocationDetailServiceImpl.getInstance();

//    @Override
//    public ResultObject process(RawDataEvent raw) {
//        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsMaterialPlanStatusEntity.class), null);
//    }

//    @Override
//    public List<ViewResultItem> process(List<RawDataEvent> list) {
//
//        return null;
//    }

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();

            PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = BeanUtil.mapToBean(rawValue.toMap(), PlanCnsMaterialPlanStatusEntity.class);

            List<ResultObject> resultObjectList = service.buildView(raw.getKey(), materialPlanStatusEntity, null);

            for (ResultObject resultObject:resultObjectList) {
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
