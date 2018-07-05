package com.jnj.pangea.omp.gdm_certainty.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsCertKeyEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_certainty.bo.OMPGdmCertaintyBo;

public class OMPGdmCertaintyServiceImpl implements ICommonService {

    private static OMPGdmCertaintyServiceImpl instance;

    public static OMPGdmCertaintyServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmCertaintyServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsCertKeyEntity cnsCertKeyEntity = (PlanCnsCertKeyEntity) o;

        OMPGdmCertaintyBo certaintyBo = new OMPGdmCertaintyBo();

        if (cnsCertKeyEntity==null){
            return resultObject;
        }
        certaintyBo.setCertaintyId(cnsCertKeyEntity.getCertaintyKey());
        certaintyBo.setLabel(cnsCertKeyEntity.getCertaintyKeyDesc());
        //rules N1
        certaintyBo.setActiveFCTERP(IConstant.VALUE.YES);
        certaintyBo.setActiveOPRERP(IConstant.VALUE.YES);
        certaintyBo.setActiveSOPERP(IConstant.VALUE.NO);

        resultObject.setBaseBo(certaintyBo);
        return resultObject;
    }
}
