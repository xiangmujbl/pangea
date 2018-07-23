package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class EDMMaterialGlobalDaoImpl extends CommonDaoImpl {

    public static final String EDM_MATERIAL_GLOBAL_V1 = "/edm/material_global_v1";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_MATERIAL_TYPE = "localMaterialType";
    public static final String LOCAL_BASE_UOM = "localBaseUom";
    public static final String PRIMARY_PLANNING_CODE = "primaryPlanningCode";
    public static final String MATERIAL_NUMBER = "materialNumber";
    public static final String LOCAL_DP_PARENT_CODE = "localDpParentCode";
    
    private static EDMMaterialGlobalDaoImpl instance;
    public static EDMMaterialGlobalDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialGlobalDaoImpl();
        }
        return instance;
    }

    public EDMMaterialGlobalV1Entity getEntityWithSourceSystemAndLocalMaterialNumber(String sourceSystem, String localMaterialNumber) {
        if (StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem).and(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
        }
        return null;
    }

    public EDMMaterialGlobalV1Entity getMaterialNumberWithLocalMaterialNumberAndSourceSystem(String sourceSystem, String matnr) {
        if (null != sourceSystem && (!(sourceSystem.isEmpty())) && null != matnr && (!(matnr.isEmpty()))) {
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem).and(LOCAL_MATERIAL_NUMBER).is(matnr).toQueryString();
            return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
        }
        return null;
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialNumber(String localMaterialNumber) {
        if (null != localMaterialNumber && (!(localMaterialNumber.isEmpty()))) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
        }
        return null;
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialType(List<String> parameterValues) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_TYPE).in(parameterValues).toQueryString();
        return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalBaseUom(String parameterValue) {
        String queryString = QueryHelper.buildCriteria(LOCAL_BASE_UOM).is(parameterValue).toQueryString();
        return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithPrimaryPlanningCode(String primaryPlanningCode) {
        String queryString = QueryHelper.buildCriteria(PRIMARY_PLANNING_CODE).is(primaryPlanningCode).toQueryString();
        return queryForObject(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntityListWithLocalMaterialNumber(String localMaterialNumber) {
        if (null != localMaterialNumber) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForList(EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
        }
        return null;
    }
}
