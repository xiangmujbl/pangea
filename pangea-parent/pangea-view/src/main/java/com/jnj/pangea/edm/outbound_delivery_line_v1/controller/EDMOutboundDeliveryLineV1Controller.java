package com.jnj.pangea.edm.outbound_delivery_line_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.LipsEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.outbound_delivery_line_v1.service.EDMOutboundDeliveryLineV1ServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMOutboundDeliveryLineV1Controller extends CommonController {

    private ICommonService service = EDMOutboundDeliveryLineV1ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), LipsEntity.class), null);
    }
}
