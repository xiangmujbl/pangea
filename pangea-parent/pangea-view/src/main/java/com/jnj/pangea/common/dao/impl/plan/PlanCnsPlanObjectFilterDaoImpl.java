package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;

public class PlanCnsPlanObjectFilterDaoImpl extends CommonDaoImpl {

    private static PlanCnsPlanObjectFilterDaoImpl instance;

    public static PlanCnsPlanObjectFilterDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanObjectFilterDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlanObjectFilterEntity getEntityWithSourceObjectAttribute1AndSourceObjectAttribute1ValueAndSourceSystem(String sourceObjectPlantAttribute, String sourceFilterPlantValue, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_OBJECT_ATTRIBUTE1).is(sourceObjectPlantAttribute)
                .and(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_OBJECT_ATTRIBUTE1_VALUE).is(sourceFilterPlantValue)
                .and(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_OBJECT_FILTER,queryString,PlanCnsPlanObjectFilterEntity.class);
    }
}
