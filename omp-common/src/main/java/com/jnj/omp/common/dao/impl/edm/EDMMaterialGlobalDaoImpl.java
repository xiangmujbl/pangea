package com.jnj.omp.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.edm.EDMMaterialGlobalV1Entity;

import java.util.List;

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
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);

    }
    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialNumber(String localMaterialNumber){
        if (null != localMaterialNumber){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);
        }
        return null;
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalMaterialType(List<String> parameterValues) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_MATERIAL_TYPE).in(parameterValues).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);
    }

    public EDMMaterialGlobalV1Entity getEntityWithLocalBaseUom(String parameterValue) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_GLOBAL_V1.LOCAL_BASE_UOM).is(parameterValue).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1,queryString,EDMMaterialGlobalV1Entity.class);
    }
}
