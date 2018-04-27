package com.jnj.pangea.omp.gdm_transport.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.CnsProcessTypeEntity;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemEntity;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemExceptionEntity;
import com.jnj.pangea.omp.gdm_transport.service.OMPGdmTransportServiceImpl;
import com.jnj.pangea.util.BeanUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OMPGdmTransportController extends CommonController {
//public class OMPGdmTransportController extends BaseController {

    private OMPGdmTransportServiceImpl service = OMPGdmTransportServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {

        RawDataValue rawValue = raw.getValue();
        LogUtil.getCoreLog().info("HEY SHANEO!!");
        LogUtil.getCoreLog().info(rawValue);

        CnsTlaneItemEntity tlaneItemEntity = BeanUtil.mapToBean(rawValue.toMap(), CnsTlaneItemEntity.class);
        CnsTlaneItemExceptionEntity tlaneItemExEntity = BeanUtil.mapToBean(rawValue.toMap(), CnsTlaneItemExceptionEntity.class);
        CnsProcessTypeEntity processTypeEntity = BeanUtil.mapToBean(rawValue.toMap(), CnsProcessTypeEntity.class);

        return service.buildView(raw.getKey(), tlaneItemEntity, tlaneItemExEntity, processTypeEntity);
    }

//    @Override
//    public List<ViewResultItem> process(List<RawDataEvent> list) {
//
//        List<ViewResultItem> result = new ArrayList<>();
//        list.forEach(raw -> {
//
//            RawDataValue rawValue = raw.getValue();
//
//            CnsTlaneItemEntity tlaneItemEntity = BeanUtil.mapToBean(rawValue.toMap(), CnsTlaneItemEntity.class);
//            CnsTlaneItemExceptionEntity tlaneItemExEntity = BeanUtil.mapToBean(rawValue.toMap(), CnsTlaneItemExceptionEntity.class);
//            CnsProcessTypeEntity processTypeEntity = BeanUtil.mapToBean(rawValue.toMap(), CnsProcessTypeEntity.class);
//
//            List<ResultObject> resultObjectList = service.buildView(raw.getKey(), tlaneItemEntity, tlaneItemExEntity, processTypeEntity);
//
//            for (ResultObject resultObject:resultObjectList) {
//                if (resultObject.isSuccess()) {
//                    BaseBo baseBo = resultObject.getBaseBo();
//                    ViewResultItem viewRaw = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
//                    result.add(viewRaw);
//                } else {
//                    if (resultObject.getFailData() != null) {
//                        FailData failData = resultObject.getFailData();
//                        ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap());
//                        result.add(viewResultItem);
//                    }
//                }
//            }
//
//        });
//        return result;
//    }






    //rawDataEvent = [2018-04-26 16:17:32,430 INFO OMPGdmTransportController.process] RawDataValueImpl [pdx=struct(_PK:{"sequenceNumber":"test","tlaneName":"test"},validTo:test,originLocation:test,materialNumber:test,processTypeId:test,validFrom:test,destinationLocation:test,leadTime:test,mode:test,sequenceNumber:test,tlaneName:test)]
    //rawDataEvent List item = [2018-04-26 16:25:44,201 INFO OMPGdmTransportController.lambda$process$0] RawDataValueImpl [pdx=struct(_PK:{"sequenceNumber":"test","tlaneName":"test"},validTo:test,originLocation:test,materialNumber:test,processTypeId:test,validFrom:test,destinationLocation:test,leadTime:test,mode:test,sequenceNumber:test,tlaneName:test)]
}
