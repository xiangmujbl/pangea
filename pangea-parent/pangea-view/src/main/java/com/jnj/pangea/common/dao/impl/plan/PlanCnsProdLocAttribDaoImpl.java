package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsProdLocAttribEntity;

public class PlanCnsProdLocAttribDaoImpl extends CommonDaoImpl {

    private static PlanCnsProdLocAttribDaoImpl instance;

    public static PlanCnsProdLocAttribDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProdLocAttribDaoImpl();
        }
        return instance;
    }

    public PlanCnsProdLocAttribEntity getEntityWithConditions(String sourceSystem, String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PROD_LOC_ATTRIB.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PROD_LOC_ATTRIB.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.CNS_PROD_LOC_ATTRIB.LOCAL_PLANT).is(localPlant)
                .toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PROD_LOC_ATTRIB, queryString, PlanCnsProdLocAttribEntity.class);
    }
}
