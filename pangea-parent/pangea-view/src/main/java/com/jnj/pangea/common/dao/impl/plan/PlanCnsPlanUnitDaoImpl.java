package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;

public class PlanCnsPlanUnitDaoImpl extends CommonDaoImpl {

    private static PlanCnsPlanUnitDaoImpl instance;

    public static PlanCnsPlanUnitDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanUnitDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlanUnitEntity getEntityWithLocalUom(String localUom) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLAN_UNIT.LOCAL_UOM).is(localUom).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_UNIT, queryString, PlanCnsPlanUnitEntity.class);
    }
}
