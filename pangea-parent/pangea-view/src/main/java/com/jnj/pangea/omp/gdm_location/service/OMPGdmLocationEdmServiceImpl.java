package com.jnj.pangea.omp.gdm_location.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMCurrencyV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlantAttrDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_location.bo.OMPGdmLocationBo;

import java.util.List;

public class OMPGdmLocationEdmServiceImpl implements ICommonService {

    private static OMPGdmLocationEdmServiceImpl instance;

    public static OMPGdmLocationEdmServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLocationEdmServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanParameterDaoImpl parameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1DaoImpl = EDMCountryV1DaoImpl.getInstance();
    private PlanCnsPlantAttrDaoImpl cnsPlantAttrDaoImpl = PlanCnsPlantAttrDaoImpl.getInstance();
    private EDMCurrencyV1DaoImpl currencyV1DaoImpl = EDMCurrencyV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMPlantV1Entity plantV1Entity = (EDMPlantV1Entity) o;

        OMPGdmLocationBo gdmLocationEdmBo = new OMPGdmLocationBo();

        //rules C1
        gdmLocationEdmBo.setLocationId(plantV1Entity.getSourceSystem() + "_" + plantV1Entity.getLocalPlant());


        //rules T2
        List<PlanCnsPlanParameterEntity> entities = parameterDao.getEntriessWithConditions(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.PLANT, plantV1Entity.getLocalPlant());
        if (!entities.isEmpty()) {
            gdmLocationEdmBo.setActiveFCTERP(IConstant.VALUE.YES);
        } else {
            gdmLocationEdmBo.setActiveFCTERP(IConstant.VALUE.NO);
        }

        //rules T7
        if (IConstant.VALUE.X.equals(plantV1Entity.getLocalPlanningRelevant())) {
            gdmLocationEdmBo.setActiveOPRERP(IConstant.VALUE.YES);
        } else {
            gdmLocationEdmBo.setActiveOPRERP(IConstant.VALUE.NO);
        }

        //rules T1
        if (IConstant.VALUE.YES.equals(gdmLocationEdmBo.getActiveFCTERP()) || IConstant.VALUE.YES.equals(gdmLocationEdmBo.getActiveOPRERP())) {
            gdmLocationEdmBo.setActive(IConstant.VALUE.YES);
        } else {
            resultObject.setBaseBo(null);
            return resultObject;
        }

        //rules T5
        gdmLocationEdmBo.setActiveSOPERP(IConstant.VALUE.NO);

        //rules T8
        PlanCnsPlantAttrEntity cnsPlantAttrEntity = cnsPlantAttrDaoImpl.getEntityWithLocalPlantAndSourceSystem(plantV1Entity.getLocalPlant(), plantV1Entity.getSourceSystem());
        if (cnsPlantAttrEntity == null) {
            resultObject.setFailData(buildFailedData(IConstant.FAILED.ERROR_CODE.T8, "Missing Location Type Id", plantV1Entity));
            return resultObject;

        }
        gdmLocationEdmBo.setLocationTypeId(cnsPlantAttrEntity.getPlanLocTypeId());

        //rules T10
        String countryId = plantV1Entity.getCountry();
        if ("".equals(countryId)) {
            resultObject.setFailData(buildFailedData(IConstant.FAILED.ERROR_CODE.T10, "Missing Country", plantV1Entity));
            return resultObject;

        }
        gdmLocationEdmBo.setCountryId(countryId);

        //rules T9
        EDMCurrencyV1Entity currencyV1Entity = currencyV1DaoImpl.getEntityWithLocalCurrencyAndSourceSystem(plantV1Entity.getLocalCurrency(), plantV1Entity.getSourceSystem());
        if (currencyV1Entity != null) {
            gdmLocationEdmBo.setCurrencyId(currencyV1Entity.getCurrencyCode());
        }

        //rules T6
        EDMCountryEntity countryEntity = countryV1DaoImpl.getEntityWithLocalCountryAndSourceSystem(plantV1Entity.getLocalCountry(), plantV1Entity.getSourceSystem());
        if (countryEntity != null) {
            gdmLocationEdmBo.setRegionId(countryEntity.getConsumerPlanningRegion());
        }

        gdmLocationEdmBo.setLabel(plantV1Entity.getLocalPlantName());

        resultObject.setBaseBo(gdmLocationEdmBo);
        return resultObject;
    }

    private FailData buildFailedData(String errorCode, String errorValue, EDMPlantV1Entity plantV1Entity) {
        FailData failData = new FailData();
        failData.setErrorCode(errorCode);
        failData.setErrorValue(errorValue);
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_LOCATION_EDM);
        failData.setSourceSystem(IConstant.FAILED.SOURCE_SYSTEM.PROJECT_ONE);
        failData.setKey1(plantV1Entity.getLocalPlant());
        failData.setKey2(plantV1Entity.getSourceSystem());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        return failData;
    }

}
