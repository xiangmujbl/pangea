package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;

import java.util.List;

public class PlanCnsTlaneControlDaoImpl extends CommonDaoImpl {

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
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_TLANE_CONTROL.SOURCE_SYSTEM_CRITICAL_PARAMETERS).is(sourceSystem).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_TLANE_CONTROL,queryString,PlanCnsTlaneControlEntity.class);
    }
}
