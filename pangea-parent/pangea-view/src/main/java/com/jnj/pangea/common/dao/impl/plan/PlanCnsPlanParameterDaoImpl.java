package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;

import java.util.List;

public class PlanCnsPlanParameterDaoImpl extends CommonDaoImpl {

    private static PlanCnsPlanParameterDaoImpl instance;

    public static PlanCnsPlanParameterDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanParameterDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsPlanParameterEntity> getEntriessWithConditions(String sourceSystem, String dataObject, String attribute, String parameter , String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER).is(parameter)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER_VALUE).is(localPlant)
                .toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public List<PlanCnsPlanParameterEntity> getEntitiesWithConditions(String sourceSystem, String dataObject, String attribute, String parameter, String inclExcl) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER).is(parameter)
                .and(IConstant.CNS_PLAN_PARAMETER.INCL_EXCL).is(inclExcl).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public List<PlanCnsPlanParameterEntity> getEntitiesWithSourceSystem(String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithConditions(String sourceSystem, String dataObject, String attribute, String parameter, String inclExcl) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER).is(parameter)
                .and(IConstant.CNS_PLAN_PARAMETER.INCL_EXCL).is(inclExcl).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithConditions(String sourceSystem,String dataObject) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithAttribute(String attribute) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(attribute).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public List<PlanCnsPlanParameterEntity> getEntityWithAttributeList(String attribute) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(attribute).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }
}
