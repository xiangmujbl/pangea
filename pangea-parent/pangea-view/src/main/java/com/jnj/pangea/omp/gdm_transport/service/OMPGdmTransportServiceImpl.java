package com.jnj.pangea.omp.gdm_transport.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.CnsProcessTypeEntity;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemEntity;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemExceptionEntity;
import com.jnj.pangea.omp.gdm_transport.bo.OMPGdmTransportBo;

import java.util.LinkedList;
import java.util.List;

public class OMPGdmTransportServiceImpl {

    private static OMPGdmTransportServiceImpl instance;

    public static OMPGdmTransportServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmTransportServiceImpl();
        }
        return instance;
    }

    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        CnsTlaneItemEntity tlaneItemEntity = (CnsTlaneItemEntity) o;

        OMPGdmTransportBo gdmTransportBo = new OMPGdmTransportBo();

        // TODO add rest of Rule logic



        resultObject.setBaseBo(gdmTransportBo);

        return resultObject;
    }


}
