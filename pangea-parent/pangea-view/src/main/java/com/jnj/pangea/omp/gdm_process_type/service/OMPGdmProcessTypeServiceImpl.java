package com.jnj.pangea.omp.gdm_process_type.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_process_type.bo.OMPGdmProcessTypebo;

public class OMPGdmProcessTypeServiceImpl implements ICommonService {

    private static OMPGdmProcessTypeServiceImpl instance;

    public static OMPGdmProcessTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProcessTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsProcessTypeEntity cnsProcessTypeEntity = (PlanCnsProcessTypeEntity) o;

        OMPGdmProcessTypebo processTypeBo = new OMPGdmProcessTypebo();

        processTypeBo.setProcessTypeId(cnsProcessTypeEntity.getProcessTypeId());
        processTypeBo.setLabel(cnsProcessTypeEntity.getProcessTypeDesc());

        // N1
        processTypeBo.setActiveOPRERP(IConstant.VALUE.YES);
        processTypeBo.setActiveSOPERP(IConstant.VALUE.NO);

        resultObject.setBaseBo(processTypeBo);
        return resultObject;
    }
}
