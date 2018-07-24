package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsProcTypeEntity;

public class PlanCnsProcTypeDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PROC_TYP = "/plan/cns_proc_type";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_PROCUREMENT_TYPE = "localProcurementType";

    private static PlanCnsProcTypeDaoImpl instance;

    public static PlanCnsProcTypeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProcTypeDaoImpl();
        }
        return instance;
    }

    public PlanCnsProcTypeEntity getEntityWithLocalProcurementTypeAndsourceSystem(String localProcurementType, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_PROCUREMENT_TYPE).is(localProcurementType).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_PROC_TYP, queryString, PlanCnsProcTypeEntity.class);
    }
}
