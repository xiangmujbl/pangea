package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
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


    public List<PlanCnsTlaneControlDaoImpl> getEntityListWithTriangulationParams(String sequenceNumber, String tlaneName, String sourceSystemCritical, String trigSysPlant, String trigSysTransaction, String triangulationDetail) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_TLANE_CONTROL.SEQUENCE_NUM).is(sequenceNumber)
                .and(IConstant.PLAN_CNS_TLANE_CONTROL.TLANE_NAME).is(tlaneName)
                .and(IConstant.PLAN_CNS_TLANE_CONTROL.SOURCE_SYSTEM_CRITICAL).is(sourceSystemCritical)
                .and(IConstant.PLAN_CNS_TLANE_CONTROL.TRIG_SYS_PLANT).is(trigSysPlant)
                .and(IConstant.PLAN_CNS_TLANE_CONTROL.TRIG_SYS_TRANSACTION).is(trigSysTransaction)
                .and(IConstant.PLAN_CNS_TLANE_CONTROL.TRIANGULATION_DETAIL).is(triangulationDetail)
                .toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_TLANE_CONTROL,queryString,PlanCnsTlaneControlEntity.class);

    }

}
