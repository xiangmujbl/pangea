package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsSplProcTypEntity;

public class PlanCnsSplProcTypDaoImpl extends CommonDaoImpl {

    private static PlanCnsSplProcTypDaoImpl instance;

    public static PlanCnsSplProcTypDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsSplProcTypDaoImpl();
        }
        return instance;
    }

    public PlanCnsSplProcTypEntity getEntityWithLocalSpecialProcurementTypeAndSourceSystem(String localSpecialProcurementType, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SPL_PROC_TYP.LOCAL_SPL_PROC_TYPE).is(localSpecialProcurementType).and(IConstant.PLAN_CNS_SPL_PROC_TYP.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_SPL_PROC_TYP, queryString, PlanCnsSplProcTypEntity.class);
    }
}
