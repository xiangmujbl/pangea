package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;

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
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.EDM_MATERIAL_GLOBAL_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithMaterialNumberAndSourceSystem(String materialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.MATERIAL_NUMBER).is(materialNumber)
                .and(IConstant.EDM_MATERIAL_GLOBAL_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
    }
}
