package com.jnj.pangea.omp.certainity.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsCertKeyEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.certainity.bo.OMPCertainityBo;

public class OMPCertainityServiceImpl implements ICommonService {

    private static OMPCertainityServiceImpl instance;

    public static OMPCertainityServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPCertainityServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsCertKeyEntity cnsCertKeyEntity = (PlanCnsCertKeyEntity) o;

        OMPCertainityBo certainityBo = new OMPCertainityBo();

        if (cnsCertKeyEntity==null){
            return resultObject;
        }
        certainityBo.setCertaintyId(cnsCertKeyEntity.getCertainityKey());
        certainityBo.setLabel(cnsCertKeyEntity.getCertainityKeyDesc());
        //rules N1
        certainityBo.setActiveFCTERP(IConstant.VALUE.YES);
        certainityBo.setActiveOPRERP(IConstant.VALUE.YES);
        certainityBo.setActiveSOPERP(IConstant.VALUE.NO);

        resultObject.setBaseBo(certainityBo);
        return resultObject;
    }
}
