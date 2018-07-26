package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.EdmMatInputEntity;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class EdmMatInputDaoImpl extends CommonDaoImpl {

    public static final String PLAN_EDM_MAT_INPUT = "/plan/edm_mat_input";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static EdmMatInputDaoImpl instance;

    public static EdmMatInputDaoImpl getInstance() {
        if (instance == null) {
            instance = new EdmMatInputDaoImpl();
        }
        return instance;
    }

    public EdmMatInputEntity getEntityWithLocalMaterialNumberAndSourceSystem(String localMaterialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();

        EdmMatInputEntity edmMatInputEntity = commonDao.queryForObject(PLAN_EDM_MAT_INPUT, queryString, EdmMatInputEntity.class);
        return edmMatInputEntity;
    }
}
