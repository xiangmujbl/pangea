package com.jnj.pangea.edm.advanced_ship_notification_v1.controller;

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
import com.jnj.pangea.common.entity.edm.EDMAdvancedShipNotificationV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.entity.project_one.LikpEntity;
import com.jnj.pangea.common.entity.project_one.LipsEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.advanced_ship_notification_v1.service.EDMAdvancedShipNotificationServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rtierne2 on 2018/5/10.
 */

public class EDMAdvancedShipNotificationController extends BaseController {

    private EDMAdvancedShipNotificationServiceImpl edmAdvanceShipNotificationService = EDMAdvancedShipNotificationServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();

            LipsEntity lipsEntity = BeanUtil.mapToBean(rawValue.toMap(), LipsEntity.class);
            LikpEntity likpEntity = BeanUtil.mapToBean(rawValue.toMap(), LikpEntity.class);

            List<ResultObject> resultObjectList = edmAdvanceShipNotificationService.buildView(raw.getKey(), lipsEntity, likpEntity);

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
