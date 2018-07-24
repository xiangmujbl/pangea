package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;

import java.util.List;

public class PlanCnsTlaneControlDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_TLANE_CONTROL = "/plan/cns_tlane_control";

    public static final String CRITICAL_PARAMETER = "criticalParameter{0}";
    public static final String CRITICAL_PARAMETER_HIGH = "criticalParameter{0}High";
    public static final String CRITICAL_PARAMETER_TABLE = "criticalParameter{0}Table";
    public static final String CRITICAL_PARAMETER_FIELD = "criticalParameter{0}Field";
    public static final String CRITICAL_PARAMETER_LOW = "criticalParameter{0}Low";
    public static final String CRITICAL_PARAMETER_OPERATOR = "criticalParameter{0}Operator";
    public static final String CRITICAL_PARAMETER_IE = "criticalParameter{0}IE";
    public static final String SOURCE_SYSTEM_CRITICAL_PARAMETERS = "sourceSystemCriticalParameters";

    private static PlanCnsTlaneControlDaoImpl instance;

    public static PlanCnsTlaneControlDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsTlaneControlDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsTlaneControlEntity> getEntityWithSourceSystemCriticalParameters(String sourceSystem) {
        if (sourceSystem.isEmpty()){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM_CRITICAL_PARAMETERS).is(sourceSystem).toQueryString();
        return queryForList(PLAN_CNS_TLANE_CONTROL,queryString,PlanCnsTlaneControlEntity.class);
    }
}