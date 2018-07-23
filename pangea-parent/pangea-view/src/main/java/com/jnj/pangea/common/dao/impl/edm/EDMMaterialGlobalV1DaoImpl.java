package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;

import java.util.List;

public class EDMMaterialGlobalV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_MATERIAL_GLOBAL_V1 = "/edm/material_global_v1";
    public static final String EDM_MATERIAL_GLOBAL_V1_COPY = "/edm/material_global_v1_copy";
    public static final String EDM_MATERIAL_GLOBAL_V1_CLONE = "/edm/material_global_v1_clone";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_MATERIAL_TYPE = "localMaterialType";
    public static final String LOCAL_BASE_UOM = "localBaseUom";
    public static final String PRIMARY_PLANNING_CODE = "primaryPlanningCode";
    public static final String MATERIAL_NUMBER = "materialNumber";
    public static final String LOCAL_DP_PARENT_CODE = "localDpParentCode";

    private static EDMMaterialGlobalV1DaoImpl instance;

    public static EDMMaterialGlobalV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialGlobalV1DaoImpl();
        }
        return instance;
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialNumber(String localMaterialNumber) {

        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithLocalDpParentCode(String localDpParentCode) {

        String queryString = QueryHelper.buildCriteria(LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithLocalDpParentCodeFromCopy(String localDpParentCode) {

        String queryString = QueryHelper.buildCriteria(LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1_COPY, queryString, EDMMaterialGlobalV1Entity.class);
    }


    public List<EDMMaterialGlobalV1Entity> getCloneEntitiesWithLocalDpParentCode(String localDpParentCode) {

        String queryString = QueryHelper.buildCriteria(LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1_CLONE, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialNumberAndSourceSystem(String localMaterialNumber, String sourceSystem) {
        if(localMaterialNumber.isEmpty() || sourceSystem.isEmpty()) {
            return null;
        }
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialNumberAndSourceSystemAndLocalDpParentCode(String localMaterialNumber, String sourceSystem, String localDpParentCode) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithMaterialNumberAndSourceSystem(String materialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(materialNumber)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithPrimaryPlanningCode(String primaryPlanningCode) {

        String queryString = QueryHelper.buildCriteria(PRIMARY_PLANNING_CODE).is(primaryPlanningCode).toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithMaterialNumber(String materialNumber) {

        String queryString = QueryHelper.buildCriteria(MATERIAL_NUMBER).is(materialNumber).toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    /**
     * Same as query for object above but returns a list.
     * Also uses local material number instead of
     * material number. Should only have single entity match
     * (using primary keys) but list easier to work with.
     * @param materialNumber
     * @param sourceSystem
     * @return
     */
    public List<EDMMaterialGlobalV1Entity> getEntitiesWithMaterialNumberAndSourceSystem (String materialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(MATERIAL_NUMBER).is(materialNumber)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithLocalMaterialNumber (String localMaterialNumber) {
        String queryString = QueryHelper.buildCriteria(MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithPrimaryPlanningCodeAndSourceSystem(String primaryPlanningCode, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(PRIMARY_PLANNING_CODE).is(primaryPlanningCode)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithLocalMaterialNumberAndSourceSystem(String localMaterialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithLocalDpParentCodeClone(String localDpParentCode) {

        String queryString = QueryHelper.buildCriteria(LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForList(EDM_MATERIAL_GLOBAL_V1_CLONE, queryString, EDMMaterialGlobalV1Entity.class);
    }
}
