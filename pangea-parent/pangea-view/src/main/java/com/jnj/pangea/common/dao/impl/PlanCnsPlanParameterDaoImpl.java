package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.CnsPlanParameterEntity;

import java.util.List;

public class PlanCnsPlanParameterDaoImpl extends CommonDaoImpl {

    private static PlanCnsPlanParameterDaoImpl instance;

    public static PlanCnsPlanParameterDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanParameterDaoImpl();
        }
        return instance;
    }

    public List<CnsPlanParameterEntity> getEntitiesWithConditions(String sourceSystem, String dataObject, String attribute, String parameter) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER).is(parameter).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, CnsPlanParameterEntity.class);
    }

    public List<CnsPlanParameterEntity> getEntitiesWithConditions(String sourceSystem, String dataObject, String attribute, String parameter, String inclExcl) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER).is(parameter)
                .and(IConstant.CNS_PLAN_PARAMETER.INCL_EXCL).is(inclExcl).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, CnsPlanParameterEntity.class);
    }
}
