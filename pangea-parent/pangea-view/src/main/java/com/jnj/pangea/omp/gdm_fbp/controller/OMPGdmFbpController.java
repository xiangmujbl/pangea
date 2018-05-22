package com.jnj.pangea.omp.gdm_fbp.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.omp.gdm_fbp.service.OMPGdmFbpServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class OMPGdmFbpController extends BaseController {

    private OMPGdmFbpServiceImpl service = OMPGdmFbpServiceImpl.getInstance();

    @Override
//    public ResultObject process(RawDataEvent raw) {
//        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialGlobalV1Entity.class), null);
//    }
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();

            EDMMaterialGlobalV1Entity materialGlobalV1Entity = BeanUtil.mapToBean(rawValue.toMap(), EDMMaterialGlobalV1Entity.class);

            List<ResultObject> resultObjectList = service.buildView(raw.getKey(), materialGlobalV1Entity, null);

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
