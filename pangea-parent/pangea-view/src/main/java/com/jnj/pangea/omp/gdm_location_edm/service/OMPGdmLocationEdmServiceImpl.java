package com.jnj.pangea.omp.gdm_location_edm.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_location_edm.bo.OMPGdmLocationEdmBo;

import java.util.List;

public class OMPGdmLocationEdmServiceImpl implements ICommonService {

    private static OMPGdmLocationEdmServiceImpl instance;

    public static OMPGdmLocationEdmServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLocationEdmServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanParameterDaoImpl parameterDao= PlanCnsPlanParameterDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMPlantV1Entity plantV1Entity = (EDMPlantV1Entity) o;

        OMPGdmLocationEdmBo gdmLocationEdmBo = new OMPGdmLocationEdmBo();
        if (null==plantV1Entity){
            return resultObject;
        }
        //rules C1
        gdmLocationEdmBo.setLocationId(plantV1Entity.getSourceSystem()+"_"+plantV1Entity.getLocalPlant());

        //rules T1
        if (plantV1Entity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)){
            gdmLocationEdmBo.setActive(IConstant.VALUE.YES);
        }

        //rules T2
        if (getFieldWithJ2(plantV1Entity)){
            gdmLocationEdmBo.setActiveFCTERP(IConstant.VALUE.YES);
        }

        //rules T3
        gdmLocationEdmBo.setActiveOPRERP(IConstant.VALUE.YES);

        gdmLocationEdmBo.setActiveSOPERP(IConstant.VALUE.NO);

        gdmLocationEdmBo.setCountryId(plantV1Entity.getCountry());
        gdmLocationEdmBo.setCurrencyId(plantV1Entity.getLocalCurrency());
        gdmLocationEdmBo.setLabel(plantV1Entity.getLocalPlantName());
        gdmLocationEdmBo.setLocationTypeId(plantV1Entity.getPlantType());
        gdmLocationEdmBo.setRegionId(plantV1Entity.getRegion());

        resultObject.setBaseBo(gdmLocationEdmBo);
        return resultObject;
    }


    /**
     * rules T2
     * @param plantV1Entity
     */
    private boolean getFieldWithJ2(EDMPlantV1Entity plantV1Entity){
        List<PlanCnsPlanParameterEntity> entities = parameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.PLANT);
        if (!entities.isEmpty()){
            for (PlanCnsPlanParameterEntity cnsPlanParameterEntity:entities) {
                if (cnsPlanParameterEntity.getParameterValue().equals(plantV1Entity.getLocalPlant())){
                    return true;
                }
            }
        }
        return false;
    }
}
