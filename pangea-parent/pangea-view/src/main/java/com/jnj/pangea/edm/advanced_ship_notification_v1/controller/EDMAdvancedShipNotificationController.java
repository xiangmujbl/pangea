package com.jnj.pangea.edm.advanced_ship_notification_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.project_one.LikpEntity;
import com.jnj.pangea.edm.advanced_ship_notification_v1.service.EDMAdvancedShipNotificationServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

/**
 * Created by rtierne2 on 2018/5/10.
 */

public class EDMAdvancedShipNotificationController extends CommonListController {

    private EDMAdvancedShipNotificationServiceImpl edmAdvanceShipNotificationService = EDMAdvancedShipNotificationServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return edmAdvanceShipNotificationService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), LikpEntity.class), null);
    }
}
