package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;


public class PlanCnsPlanUnitDaoImpl extends CommonDaoImpl {
    private static PlanCnsPlanUnitDaoImpl instance;

    public static PlanCnsPlanUnitDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanUnitDaoImpl();
        }
        return instance;
    }

    public CnsPlanUnitEntity getCnsPlanUnitEntityWithLocalUom(String localUom) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_UNIT.LOCAL_UOM).is(localUom).toQueryString();
        return queryForObject(IConstant.REGION.CNS_PLAN_UNIT,queryString,CnsPlanUnitEntity.class);
    }

    public CnsPlanUnitEntity getEntityWithLocalUomAndPlanFlag(String localBaseUom,String planFlag) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_UNIT.LOCAL_UOM).is(localBaseUom)
                .and(IConstant.CNS_PLAN_UNIT.PLAN_FLAG).is(planFlag).toQueryString();
        return queryForObject(IConstant.REGION.CNS_PLAN_UNIT,queryString,CnsPlanUnitEntity.class);
    }
}
