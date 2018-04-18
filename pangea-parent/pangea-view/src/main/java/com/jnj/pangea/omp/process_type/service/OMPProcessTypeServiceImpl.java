package com.jnj.pangea.omp.process_type.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.process_type.bo.OMPProcessTypeBo;

public class OMPProcessTypeServiceImpl implements ICommonService {

    private static OMPProcessTypeServiceImpl instance;

    public static OMPProcessTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPProcessTypeServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsProcessTypeEntity cnsProcessTypeEntity = (PlanCnsProcessTypeEntity) o;

        OMPProcessTypeBo processTypeBo = new OMPProcessTypeBo();

        processTypeBo.setProcessTypeId(cnsProcessTypeEntity.getProcessTypeId());
        processTypeBo.setLabel(cnsProcessTypeEntity.getProcessTypeDescription());

        // N1
        processTypeBo.setActiveOPRERP(IConstant.VALUE.YES);
        processTypeBo.setActiveSOPERP(IConstant.VALUE.NO);

        resultObject.setBaseBo(processTypeBo);
        return resultObject;
    }
}
