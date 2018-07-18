package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;
import org.apache.commons.lang.StringUtils;
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

    public List<PlanCnsTlaneControlEntity> getMatchingEntities(String sourceSystem, String localPlant) {
        if (StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(localPlant)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_TLANE_CONTROL.SOURCE_SYSTEM_CRITICAL_PARAMETERS).is(sourceSystem)
                    .and(IConstant.PLAN_CNS_TLANE_CONTROL.TRIG_SYS_PLANT).is(localPlant)
                    .toQueryString();

            return queryForObject(IConstant.REGION.PLAN_CNS_TLANE_CONTROL, queryString, PlanCnsTlaneControlEntity.class);
        }
        return null;
    }

    public List<PlanCnsTlaneControlEntity> getMatchingEntities(String sourceSystem, String localPlant,
                                                               String sysTransaction, String triangulationDetail) {
        if (StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(localPlant) &&
                StringUtils.isNotBlank(sysTransaction) && StringUtils.isNotBlank(triangulationDetail)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_TLANE_CONTROL.SOURCE_SYSTEM_CRITICAL_PARAMETERS).is(sourceSystem)
                    .and(IConstant.PLAN_CNS_TLANE_CONTROL.TRIG_SYS_PLANT).is(localPlant)
                    .and(IConstant.PLAN_CNS_TLANE_CONTROL.TRIG_SYS_TRANSACTION).is(sysTransaction)
                    .and(IConstant.PLAN_CNS_TLANE_CONTROL.TRIANGULATION_DETAIL).is(triangulationDetail)
                    .toQueryString();

            return queryForObject(IConstant.REGION.PLAN_CNS_TLANE_CONTROL, queryString, PlanCnsTlaneControlEntity.class);
        }
        return null;
    }
}
