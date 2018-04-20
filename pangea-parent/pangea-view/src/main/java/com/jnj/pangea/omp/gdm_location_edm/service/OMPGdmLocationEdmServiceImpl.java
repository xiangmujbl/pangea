package com.jnj.pangea.omp.gdm_location_edm.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
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
    private EDMCountryV1DaoImpl  countryV1DaoImpl= EDMCountryV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMPlantV1Entity plantV1Entity = (EDMPlantV1Entity) o;

        OMPGdmLocationEdmBo gdmLocationEdmBo = new OMPGdmLocationEdmBo();
        //rules C1
        gdmLocationEdmBo.setLocationId(plantV1Entity.getSourceSystem()+"_"+plantV1Entity.getLocalPlant());


        //rules T2
        List<PlanCnsPlanParameterEntity> entities = parameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.PLANT);
        if (entities!=null && entities.size()>0){
            for (PlanCnsPlanParameterEntity cnsPlanParameterEntity:entities){
               if (cnsPlanParameterEntity!=null){
                   if (cnsPlanParameterEntity.getParameterValue().equals(plantV1Entity.getLocalPlant())){
                       gdmLocationEdmBo.setActiveFCTERP(IConstant.VALUE.YES);
                   }else if (!cnsPlanParameterEntity.getAttribute().equals(IConstant.VALUE.DP_RELEVANT)){
                       gdmLocationEdmBo.setActiveFCTERP(IConstant.VALUE.NO);
                   }
               }
            }
        }

        //rules T7
        if (IConstant.VALUE.X.equals(plantV1Entity.getLocalPlanningRelevant())){
            gdmLocationEdmBo.setActiveOPRERP(IConstant.VALUE.YES);
        }else{
            gdmLocationEdmBo.setActiveOPRERP(IConstant.VALUE.NO);
        }

        //rules T1
        if (IConstant.VALUE.YES.equals(gdmLocationEdmBo.getActiveFCTERP())||IConstant.VALUE.YES.equals(gdmLocationEdmBo.getActiveOPRERP())){

        }

        //rules T5
        gdmLocationEdmBo.setActiveSOPERP(IConstant.VALUE.NO);

        gdmLocationEdmBo.setCountryId(plantV1Entity.getCountry());
        gdmLocationEdmBo.setCurrencyId(plantV1Entity.getLocalCurrency());
        gdmLocationEdmBo.setLabel(plantV1Entity.getLocalPlantName());
        gdmLocationEdmBo.setLocationTypeId(plantV1Entity.getPlantType());

        //rules T6
        EDMCountryEntity countryEntity = countryV1DaoImpl.getEntityWithLocalCountryAndSourceSystem(plantV1Entity.getLocalCountry(), plantV1Entity.getSourceSystem());
        if (countryEntity!=null){
            gdmLocationEdmBo.setRegionId(countryEntity.getConsumerPlanningRegion());
        }

        resultObject.setBaseBo(gdmLocationEdmBo);
        return resultObject;
    }

}
