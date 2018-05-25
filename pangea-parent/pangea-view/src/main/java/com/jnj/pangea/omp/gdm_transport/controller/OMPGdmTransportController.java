package com.jnj.pangea.omp.gdm_transport.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemEntity;
import com.jnj.pangea.omp.gdm_transport.service.OMPGdmTransportServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmTransportController extends CommonController {

    private OMPGdmTransportServiceImpl service = OMPGdmTransportServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {

        RawDataValue rawValue = raw.getValue();
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(rawValue.toMap(), CnsTlaneItemEntity.class), null);
    }
}
