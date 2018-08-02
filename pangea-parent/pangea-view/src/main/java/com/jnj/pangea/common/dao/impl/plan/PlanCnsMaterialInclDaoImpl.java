package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsMaterialInclDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_MATERIAL_INCL = "/plan/cns_material_incl";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String PLANNING_TYPE = "planningType";
    public static final String LOCAL_PLANT = "localPlant";
    public static final String INCLUSION_TYPE = "inclusionType";
    public static final String LOCAL_SOURCE_SYSTEM = "localSourceSystem";

    private static final String NP = "NP";

    private static PlanCnsMaterialInclDaoImpl instance;

    public static PlanCnsMaterialInclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialInclDaoImpl();
        }
        return instance;
    }

    public CnsMaterialInclEntity getCnsMaterialInclEntityWithLocalMaterialNumberAndPlanningType() {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(NP)
                .and(PLANNING_TYPE).is(NP).toQueryString();
        return queryForObject(PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);

    }

    public CnsMaterialInclEntity getEntityWithLocalSourceSystem(String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_SOURCE_SYSTEM)
                .is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
    }

    public List<CnsMaterialInclEntity> getAllEntity() {
        String queryString = QueryHelper.buildCriteria().toQueryString();
        return queryForList(PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
    }

    public CnsMaterialInclEntity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        if (null != localMaterialNumber && null != localPlant) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
        }
        return null;
    }

    public CnsMaterialInclEntity getEntityWithConditions(String localMaterialNumber, String localPlant, String inclusionType, String planningType) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(inclusionType) && StringUtils.isNotEmpty(planningType)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(LOCAL_PLANT).is(localPlant)
                    .and(INCLUSION_TYPE).is(inclusionType)
                    .and(PLANNING_TYPE).is(planningType).toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
        }
        return null;
    }

    public CnsMaterialInclEntity getEntityWithLocalMaterialNumberAndPlanningType(String localMaterialNumber, String planningType) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(planningType)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(PLANNING_TYPE).is(planningType).toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
        }
        return null;
    }
}
