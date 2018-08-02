package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class PlanCnsTlaneControlDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_TLANE_CONTROL = "/plan/cns_tlane_control";

    // field names for this region
    public static final String SEQUENCE_NUMBER = "sequenceNumber";
    public static final String TLANE_NAME = "tlaneName";
    public static final String TRIG_SYS_PLANT = "trigSysPlant";
    public static final String TRIG_SYS_TRANSACTION = "trigSysTransaction";
    public static final String TRIANGULATION_DETAIL = "triangulationDetail";
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

    public List<PlanCnsTlaneControlEntity> getEntityWithSourcePlantTranTriangulation(String sourceSystem, String localPlant,
                                                                                     String sysTransaction, String triangulationDetail) {
        if (StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(localPlant) &&
                StringUtils.isNotBlank(sysTransaction) && StringUtils.isNotBlank(triangulationDetail)) {
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM_CRITICAL_PARAMETERS).is(sourceSystem)
                    .and(TRIG_SYS_PLANT).is(localPlant)
                    .and(TRIG_SYS_TRANSACTION).is(sysTransaction)
                    .and(TRIANGULATION_DETAIL).is(triangulationDetail)
                    .toQueryString();

            return queryForList(PLAN_CNS_TLANE_CONTROL, queryString, PlanCnsTlaneControlEntity.class);
        }
        return null;
    }


    public List<PlanCnsTlaneControlDaoImpl> getEntityListWithTriangulationParams(String sequenceNumber, String tlaneName, String sourceSystemCritical, String trigSysPlant, String trigSysTransaction, String triangulationDetail) {
        String queryString = QueryHelper.buildCriteria(SEQUENCE_NUMBER).is(sequenceNumber)
                .and(TLANE_NAME).is(tlaneName)
                .and(SOURCE_SYSTEM_CRITICAL_PARAMETERS).is(sourceSystemCritical)
                .and(TRIG_SYS_PLANT).is(trigSysPlant)
                .and(TRIG_SYS_TRANSACTION).is(trigSysTransaction)
                .and(TRIANGULATION_DETAIL).is(triangulationDetail)
                .toQueryString();
        return queryForList(PLAN_CNS_TLANE_CONTROL,queryString,PlanCnsTlaneControlEntity.class);

    }

    public List<PlanCnsTlaneControlDaoImpl> getEntityListWithTriangulationParamsAnySequence(String sourceSystemCritical, String trigSysPlant, String trigSysTransaction, String triangulationDetail) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM_CRITICAL_PARAMETERS).is(sourceSystemCritical)
                .and(TRIG_SYS_PLANT).is(trigSysPlant)
                .and(TRIG_SYS_TRANSACTION).is(trigSysTransaction)
                .and(TRIANGULATION_DETAIL).is(triangulationDetail)
                .toQueryString();
        return queryForList(PLAN_CNS_TLANE_CONTROL,queryString,PlanCnsTlaneControlEntity.class);

    }

}