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

    public PlanCnsPlanObjectFilterEntity getEntityWithSourceObjectTechNameAndSourceSystem(String sourceObjectTechName, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_OBJECT_TECHNAME).is(sourceObjectTechName)
                .and(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_OBJECT_FILTER,queryString,PlanCnsPlanObjectFilterEntity.class);
    }

    public PlanCnsPlanObjectFilterEntity getEntityWithSourceObjectTechNameAndSourceSystemAndSourceObjectAttribute1AndValue1(String sourceObjectTechName, String sourceSystem, String attribute1, String attrValue1) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_OBJECT_TECHNAME).is(sourceObjectTechName)
                .and(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_OBJECT_PLANT_ATTRIBUTE1).is(attribute1)
                .and(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_OBJECT_ATTRIBUTE1_VALUE).is(attrValue1)
                .toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_OBJECT_FILTER,queryString,PlanCnsPlanObjectFilterEntity.class);
    }
}
