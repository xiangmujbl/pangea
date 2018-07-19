package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class PlanCnsTlaneControlDaoImpl extends CommonDaoImpl {

    // field names for this region
    public static final String TRIG_SYS_PLANT = "trigSysPlant";
    public static final String TRIG_SYS_TRANSACTION = "trigSysTransaction";
    public static final String TRIANGULATION_DETAIL = "triangulationDetail";

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

    public List<PlanCnsTlaneControlEntity> getEntityWithSourcePlantTranTriangulation(String sourceSystem, String localPlant,
                                                                                     String sysTransaction, String triangulationDetail) {
        if (StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(localPlant) &&
                StringUtils.isNotBlank(sysTransaction) && StringUtils.isNotBlank(triangulationDetail)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_TLANE_CONTROL.SOURCE_SYSTEM_CRITICAL_PARAMETERS).is(sourceSystem)
                    .and(TRIG_SYS_PLANT).is(localPlant)
                    .and(TRIG_SYS_TRANSACTION).is(sysTransaction)
                    .and(TRIANGULATION_DETAIL).is(triangulationDetail)
                    .toQueryString();

            return queryForList(IConstant.REGION.PLAN_CNS_TLANE_CONTROL, queryString, PlanCnsTlaneControlEntity.class);
        }
        return null;
    }
}
