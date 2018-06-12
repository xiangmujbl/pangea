package com.jnj.pangea.omp.gdm_certainity.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsCertKeyEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_certainity.bo.OMPGdmCertainityBo;

public class OMPGdmCertainityServiceImpl implements ICommonService {

    private static OMPGdmCertainityServiceImpl instance;

    public static OMPGdmCertainityServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmCertainityServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsCertKeyEntity cnsCertKeyEntity = (PlanCnsCertKeyEntity) o;

        OMPGdmCertainityBo certainityBo = new OMPGdmCertainityBo();

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
