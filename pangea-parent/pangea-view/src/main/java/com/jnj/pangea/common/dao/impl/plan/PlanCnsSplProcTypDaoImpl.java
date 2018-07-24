package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsSplProcTypEntity;

public class PlanCnsSplProcTypDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_SPL_PROC_TYP = "/plan/cns_spl_proc_typ";

    public static final String LOCAL_SPL_PROC_TYPE = "localSplProcType";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static PlanCnsSplProcTypDaoImpl instance;

    public static PlanCnsSplProcTypDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsSplProcTypDaoImpl();
        }
        return instance;
    }

    public PlanCnsSplProcTypEntity getEntityWithLocalSpecialProcurementTypeAndSourceSystem(String localSpecialProcurementType, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_SPL_PROC_TYPE).is(localSpecialProcurementType).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_SPL_PROC_TYP, queryString, PlanCnsSplProcTypEntity.class);
    }
}
