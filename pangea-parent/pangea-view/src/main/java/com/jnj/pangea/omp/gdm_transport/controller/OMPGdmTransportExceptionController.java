package com.jnj.pangea.omp.gdm_transport.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemExceptionEntity;
import com.jnj.pangea.omp.gdm_transport.service.OMPGdmTransportExceptionServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmTransportExceptionController extends CommonController {

    private OMPGdmTransportExceptionServiceImpl service = OMPGdmTransportExceptionServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {

        RawDataValue rawValue = raw.getValue();

        return service.buildView(raw.getKey(), BeanUtil.mapToBean(rawValue.toMap(), CnsTlaneItemExceptionEntity.class), null);
    }

    //LogUtil.getCoreLog().info(rawValue.toMap());
    //rawDataEvent = [2018-04-26 16:17:32,430 INFO OMPGdmTransportController.process] RawDataValueImpl [pdx=struct(_PK:{"sequenceNumber":"test","tlaneName":"test"},validTo:test,originLocation:test,materialNumber:test,processTypeId:test,validFrom:test,destinationLocation:test,leadTime:test,mode:test,sequenceNumber:test,tlaneName:test)]
    //rawDataEvent List item = [2018-04-26 16:25:44,201 INFO OMPGdmTransportController.lambda$process$0] RawDataValueImpl [pdx=struct(_PK:{"sequenceNumber":"test","tlaneName":"test"},validTo:test,originLocation:test,materialNumber:test,processTypeId:test,validFrom:test,destinationLocation:test,leadTime:test,mode:test,sequenceNumber:test,tlaneName:test)]
}
