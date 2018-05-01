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

    public PlanCnsPlanObjectFilterEntity getEntityWithSourceObjectPlantAttributeAndSourceFilterPlantValue(String sourceObjectPlantAttribute, String sourceFilterPlantValue) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_OBJECT_PLANT_ATTRIBUTE).is(sourceObjectPlantAttribute)
                .and(IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_FILTER_PLANT_VALUE).is(sourceFilterPlantValue).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_OBJECT_FILTER,queryString,PlanCnsPlanObjectFilterEntity.class);
    }
}
