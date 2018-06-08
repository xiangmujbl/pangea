package com.jnj.pangea.omp.gdm_region.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanRegionEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_region.bo.OMPGdmRegionBo;

public class OMPGdmRegionServiceImpl implements ICommonService {

    private static OMPGdmRegionServiceImpl instance;

    public static OMPGdmRegionServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmRegionServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsPlanRegionEntity cnsPlanRegionEntity = (PlanCnsPlanRegionEntity) o;

        OMPGdmRegionBo gdmRegionBo = new OMPGdmRegionBo();

        gdmRegionBo.setRegionId(cnsPlanRegionEntity.getPlanningRegionId());
        gdmRegionBo.setRegionDescription(cnsPlanRegionEntity.getPlanningRegionDesc());

        gdmRegionBo.setActiveFCTERP(IConstant.VALUE.YES);
        gdmRegionBo.setActiveOPRERP(IConstant.VALUE.YES);
        gdmRegionBo.setActiveSOPERP(IConstant.VALUE.NO);

        resultObject.setBaseBo(gdmRegionBo);
        return resultObject;
    }
}
