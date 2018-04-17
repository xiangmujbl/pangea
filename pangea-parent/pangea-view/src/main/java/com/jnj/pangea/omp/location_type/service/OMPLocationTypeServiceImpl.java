package com.jnj.pangea.omp.location_type.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsLocationTypeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.location_type.bo.OMPLocationTypeBo;

public class OMPLocationTypeServiceImpl implements ICommonService {

    private static OMPLocationTypeServiceImpl instance;

    public static OMPLocationTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPLocationTypeServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsLocationTypeEntity cnsLocationTypeEntity = (PlanCnsLocationTypeEntity) o;

        OMPLocationTypeBo locationTypeBo = new OMPLocationTypeBo();

        locationTypeBo.setLocationTypeId(cnsLocationTypeEntity.getPlanLocTypeId());
        locationTypeBo.setLabel(cnsLocationTypeEntity.getPlanLocTypeDesc());
        locationTypeBo.setActiveFCTERP(IConstant.VALUE.YES);
        locationTypeBo.setActiveOPRERP(IConstant.VALUE.YES);
        locationTypeBo.setActiveSOPERP(IConstant.VALUE.NO);

        resultObject.setBaseBo(locationTypeBo);
        return resultObject;
    }
}
