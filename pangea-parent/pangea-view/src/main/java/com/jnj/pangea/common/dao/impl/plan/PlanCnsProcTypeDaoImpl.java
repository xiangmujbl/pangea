package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsProcTypeEntity;

public class PlanCnsProcTypeDaoImpl extends CommonDaoImpl {

    private static PlanCnsProcTypeDaoImpl instance;

    public static PlanCnsProcTypeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProcTypeDaoImpl();
        }
        return instance;
    }

    public PlanCnsProcTypeEntity getEntityWithLocalProcurementTypeAndsourceSystem(String localProcurementType, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PROC_TYP.LOCAL_PROCUREMENT_TYPE).is(localProcurementType).and(IConstant.PLAN_CNS_PROC_TYP.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PROC_TYP, queryString, PlanCnsProcTypeEntity.class);
    }
}
