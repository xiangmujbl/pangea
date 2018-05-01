package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;

public class EDMMaterialGlobalDaoImpl extends CommonDaoImpl {
    private static EDMMaterialGlobalDaoImpl instance;
    public static EDMMaterialGlobalDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialGlobalDaoImpl();
        }
        return instance;
    }

    public EDMMaterialGlobalV1Entity getMaterialNumberWithLocalMaterialNumberAndSourceSystem(String sourceSystem, String matnr) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_NUMBER).is(matnr).toQueryString();
        LogUtil.getCoreLog().info("queryString:{}",queryString);
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);

    }
    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialNumber(String localMaterialNumber){
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialType(String parameterValue) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_TYPE).is(parameterValue).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalBaseUom(String parameterValue) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_BASE_UOM).is(parameterValue).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithPrimaryPlanningCode(String primaryPlanningCode) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.PRIMARY_PLANNING_CODE).is(primaryPlanningCode).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);
    }
}
