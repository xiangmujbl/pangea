package com.jnj.pangea.edm.outbound_delivery_header.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.LikpEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.outbound_delivery_header.service.EDMOutboundDeliveryHeaderServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMOutboundDeliveryHeaderController extends CommonController {

    private ICommonService service = EDMOutboundDeliveryHeaderServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), LikpEntity.class), null);
    }
}
