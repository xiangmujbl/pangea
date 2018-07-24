package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;

import java.util.List;

public class PlanCnsProcessTypeDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PROCESS_TYPE = "/plan/cns_process_type";

    public static final String PROCESS_TYPE_ID = "processTypeId";
    public static final String PROCESS_TYPE_DESCRIPTION = "processTypeDesc";

    private static PlanCnsProcessTypeDaoImpl instance;

    public static PlanCnsProcessTypeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProcessTypeDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsProcessTypeEntity> getAllEntityTest() {
        String queryString = QueryHelper.buildCriteria().toQueryString();
        return queryForList(PLAN_CNS_PROCESS_TYPE,queryString,PlanCnsProcessTypeEntity.class);
    }

    public PlanCnsProcessTypeEntity getEntityWithConditions(String processTypeID) {
        if(processTypeID != null && !(processTypeID.isEmpty())) {
            String queryString = QueryHelper.buildCriteria(PROCESS_TYPE_ID).is(processTypeID).toQueryString();
            return queryForObject(PLAN_CNS_PROCESS_TYPE, queryString, PlanCnsProcessTypeEntity.class);
        }
        return null;
    }

    public PlanCnsProcessTypeEntity getCnsProcessTypeById(String processTypeId) {
        String queryString = QueryHelper.buildCriteria(PROCESS_TYPE_ID).is(processTypeId).toQueryString();
        return queryForObject(PLAN_CNS_PROCESS_TYPE, queryString, PlanCnsProcessTypeEntity.class);
    }
}

