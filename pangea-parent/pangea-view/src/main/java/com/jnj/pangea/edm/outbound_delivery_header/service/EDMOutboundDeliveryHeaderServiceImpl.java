package com.jnj.pangea.edm.outbound_delivery_header.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.service.ICommonService;

public class EDMOutboundDeliveryHeaderServiceImpl implements ICommonService{

    private static EDMOutboundDeliveryHeaderServiceImpl instance;

    public static EDMOutboundDeliveryHeaderServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMOutboundDeliveryHeaderServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        return resultObject;
    }
}
