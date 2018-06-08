package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsMaterialInclDaoImpl extends CommonDaoImpl {
    private static PlanCnsMaterialInclDaoImpl instance;

    public static PlanCnsMaterialInclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialInclDaoImpl();
        }
        return instance;
    }

    public CnsMaterialInclEntity getCnsMaterialInclEntityWithLocalMaterialNumberAndPlanningType() {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_INCL.LOCAL_MATERIAL_NUMBER).is(IConstant.VALUE.NP)
                .and(IConstant.CNS_MATERIAL_INCL.PLANNING_TYPE).is(IConstant.VALUE.NP).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);

    }

    public CnsMaterialInclEntity getEntityWithLocalSourceSystem(String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM)
                .is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, CnsMaterialInclEntity.class);
    }

    public List<CnsMaterialInclEntity> getAllEntity() {
        String queryString = QueryHelper.buildCriteria().toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
    }

    public CnsMaterialInclEntity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        if (null != localMaterialNumber && null != localPlant) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_INCL.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_MATERIAL_INCL.LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
        }
        return null;
    }

    public CnsMaterialInclEntity getEntityWithConditions(String localMaterialNumber, String localPlant, String inclusionType, String planningType) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(inclusionType) && StringUtils.isNotEmpty(planningType)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_INCL.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_MATERIAL_INCL.LOCAL_PLANT).is(localPlant)
                    .and(IConstant.CNS_MATERIAL_INCL.INCLUSION_TYPE).is(inclusionType)
                    .and(IConstant.CNS_MATERIAL_INCL.PLANNING_TYPE).is(planningType).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
        }
        return null;
    }

    public CnsMaterialInclEntity getEntityWithLocalMaterialNumberAndPlanningType(String localMaterialNumber, String planningType) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(planningType)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_INCL.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_MATERIAL_INCL.PLANNING_TYPE).is(planningType).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_INCL, queryString, CnsMaterialInclEntity.class);
        }
        return null;
    }
}
