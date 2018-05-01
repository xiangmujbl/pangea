package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsProcessTypeEntity;


public class PlanCnsProcessTypeDaoImpl extends CommonDaoImpl {
    private static PlanCnsProcessTypeDaoImpl instance;

    public static PlanCnsProcessTypeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProcessTypeDaoImpl();
        }
        return instance;
    }

    public CnsProcessTypeEntity getCnsProcessTypeById(String processTypeId) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PROCESS_TYPE.PROCESS_TYPE_ID).is(processTypeId).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PROCESS_TYPE,queryString,CnsProcessTypeEntity.class);
    }


}
