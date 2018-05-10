package com.jnj.pangea.edm.advanced_ship_notification_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMAdvancedShipNotificationV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.advanced_ship_notification_v1.service.EDMAdvancedShipNotificationServiceImpl;
import com.jnj.pangea.util.BeanUtil;

/**
 * Created by rtierne2 on 2018/5/10.
 */

public class EDMAdvancedShipNotificationController extends CommonController {

    private ICommonService edmAdvanceShipNotificationService = EDMAdvancedShipNotificationServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return edmAdvanceShipNotificationService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMAdvancedShipNotificationV1Entity.class), null);
    }

}
