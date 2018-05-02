package com.jnj.pangea.omp.gdm_location_type.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsLocTypeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_location_type.bo.OMPGdmLocationTypeBo;

public class OMPGdmLocationTypeServiceImpl implements ICommonService {

    private static OMPGdmLocationTypeServiceImpl instance;

    public static OMPGdmLocationTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLocationTypeServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsLocTypeEntity cnsLocTypeEntity = (PlanCnsLocTypeEntity) o;

        OMPGdmLocationTypeBo gdmLocationTypeBo = new OMPGdmLocationTypeBo();
        // TODO add logic
        gdmLocationTypeBo.setLocationTypeId(cnsLocTypeEntity.getPlanLocTypeId());
        gdmLocationTypeBo.setActiveFCTERP(IConstant.VALUE.YES);
        gdmLocationTypeBo.setActiveOPRERP(IConstant.VALUE.YES);
        gdmLocationTypeBo.setActiveSOPERP(IConstant.VALUE.NO);

        gdmLocationTypeBo.setLABEL(cnsLocTypeEntity.getPlanLocTypeDesc());

        resultObject.setBaseBo(gdmLocationTypeBo);
        return resultObject;
    }
}
