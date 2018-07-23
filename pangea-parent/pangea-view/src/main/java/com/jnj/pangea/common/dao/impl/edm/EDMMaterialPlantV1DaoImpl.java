package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMaterialPlantV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_MATERIAL_PLANT_V1 = "/edm/material_plant_v1";

    public static final String LOCAL_PLANT = "localPlant";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String PRIMARY_PLANNING_CODE = "primaryPlanningCode";
    
    private static EDMMaterialPlantV1DaoImpl instance;

    public static EDMMaterialPlantV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialPlantV1DaoImpl();
        }
        return instance;
    }

    public EDMMaterialPlantV1Entity getEntityWithMaterialNumberPlantNumberSourceSystem(String sourceSystem, String localPlant,String materialNumber) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_PLANT).is(localPlant)
                .and(LOCAL_MATERIAL_NUMBER).is(materialNumber).toQueryString();
        return queryForObject(EDM_MATERIAL_PLANT_V1,queryString,EDMMaterialPlantV1Entity.class);
    }

    public EDMMaterialPlantV1Entity getEntityWithLocalMaterialNumberLocalPlantNumberSourceSystem(String localMaterialNumber, String localPlantNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_PLANT).is(localPlantNumber)
                .and(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForObject(EDM_MATERIAL_PLANT_V1,queryString,EDMMaterialPlantV1Entity.class);
    }

    public EDMMaterialPlantV1Entity getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(String sourceSystem, String localPlant, String localMaterialNumber) {
        if (sourceSystem != null && localPlant != null && localMaterialNumber != null && ( (!(sourceSystem.isEmpty())) && (!(localPlant.isEmpty())) && (!(localMaterialNumber.isEmpty())))) {
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                    .and(LOCAL_PLANT).is(localPlant)
                    .and(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForObject(EDM_MATERIAL_PLANT_V1,queryString,EDMMaterialPlantV1Entity.class);
        }
        return null;
    }

    public EDMMaterialPlantV1Entity getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber( String localMaterialNumber) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForObject(EDM_MATERIAL_PLANT_V1,queryString,EDMMaterialPlantV1Entity.class);
    }

    public List<EDMMaterialPlantV1Entity> getEntitiesWithLocalMaterialNumberLocalPlantNumberSourceSystem(String localMaterialNumber, String localPlantNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_PLANT).is(localPlantNumber)
                .and(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForList(EDM_MATERIAL_PLANT_V1,queryString,EDMMaterialPlantV1Entity.class);
    }

    public List<EDMMaterialPlantV1Entity> getEntityWithLocalMaterialNumber(String localMaterialNumber) {
        if (StringUtils.isNotEmpty(localMaterialNumber)){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForList(EDM_MATERIAL_PLANT_V1,queryString,EDMMaterialPlantV1Entity.class);
        }
        return null;
    }


}
