package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;

import java.util.List;

public class PlanCnsProcessTypeDaoImpl extends CommonDaoImpl {

    private static PlanCnsProcessTypeDaoImpl instance;

    public static PlanCnsProcessTypeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProcessTypeDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsProcessTypeEntity> getAllEntityTest() {
        String queryString = QueryHelper.buildCriteria().toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PROCESS_TYPE,queryString,PlanCnsProcessTypeEntity.class);
    }

    public PlanCnsProcessTypeEntity getEntityWithConditions(String processTypeDesc) {
        if(processTypeDesc != null && !(processTypeDesc.isEmpty())) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PROCESS_TYPE.PROCESS_TYPE_DESCRIPTION).is(processTypeDesc).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_PROCESS_TYPE, queryString, PlanCnsProcessTypeEntity.class);
        }
        return null;
    }

    public PlanCnsProcessTypeEntity getCnsProcessTypeById(String processTypeId) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PROCESS_TYPE.PROCESS_TYPE_ID).is(processTypeId).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PROCESS_TYPE, queryString, PlanCnsProcessTypeEntity.class);
    }
}

