package com.jnj.pangea.omp.gdm_supply.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.project_one.AfkoEntity;
import com.jnj.pangea.omp.gdm_supply.service.OMPGdmSupplyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class OMPGdmSupplyController extends BaseController {

    private OMPGdmSupplyServiceImpl service = OMPGdmSupplyServiceImpl.getInstance();


    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();

            list.forEach(raw -> {
                RawDataValue rawValue = raw.getValue();

                EDMSourceListV1Entity sourceListV1Entity = BeanUtil.mapToBean(rawValue.toMap(), EDMSourceListV1Entity.class);
                List<ResultObject> resultObjectList = service.buildView(raw.getKey(), sourceListV1Entity, null);

                    for (ResultObject resultObject : resultObjectList) {
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


