package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class EDMMaterialGlobalV1DaoImpl extends CommonDaoImpl {

    private static EDMMaterialGlobalV1DaoImpl instance;

    public static EDMMaterialGlobalV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialGlobalV1DaoImpl();
        }
        return instance;
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialNumber(String localMaterialNumber) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithLocalDpParentCode(String localDpParentCode) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getCloneEntitiesWithLocalDpParentCode(String localDpParentCode) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1_CLONE.LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1_CLONE, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialNumberAndSourceSystem(String localMaterialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.EDM_MATERIAL_GLOBAL_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithMaterialNumberAndSourceSystem(String materialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_NUMBER).is(materialNumber)
                .and(IConstant.EDM_MATERIAL_GLOBAL_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        LogUtil.getCoreLog().info("----------------------queryString_EDMMaterialGlobalV1Entity--------------------"+queryString);
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithPrimaryPlanningCode(String primaryPlanningCode) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.PRIMARY_PLANNING_CODE).is(primaryPlanningCode).toQueryString();
        return queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithMaterialNumber(String materialNumber) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.MATERIAL_NUMBER).is(materialNumber).toQueryString();
        return queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
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
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.MATERIAL_NUMBER).is(materialNumber)
                .and(IConstant.EDM_MATERIAL_GLOBAL_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithLocalMaterialNumber (String localMaterialNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public List<EDMMaterialGlobalV1Entity> getEntitiesWithPrimaryPlanningCodeAndSourceSystem(String primaryPlanningCode, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.PRIMARY_PLANNING_CODE).is(primaryPlanningCode)
                .and(IConstant.EDM_MATERIAL_GLOBAL_V1.SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();
        return queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }
}
