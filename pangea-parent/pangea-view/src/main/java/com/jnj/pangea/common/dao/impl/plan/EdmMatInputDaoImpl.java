package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.EdmMatInputEntity;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class EdmMatInputDaoImpl extends CommonDaoImpl {

    private static EdmMatInputDaoImpl instance;

    public static EdmMatInputDaoImpl getInstance() {
        if (instance == null) {
            instance = new EdmMatInputDaoImpl();
        }
        return instance;
    }

    public EdmMatInputEntity getEntityWithLocalMaterialNumberAndSourceSystem(String localMaterialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_EDM_MAT_INPUT.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.PLAN_EDM_MAT_INPUT.SOURCE_SYSTEM).is(sourceSystem).toQueryString();

        EdmMatInputEntity edmMatInputEntity = commonDao.queryForObject(IConstant.REGION.PLAN_EDM_MAT_INPUT, queryString, EdmMatInputEntity.class);
        return edmMatInputEntity;
    }
}
