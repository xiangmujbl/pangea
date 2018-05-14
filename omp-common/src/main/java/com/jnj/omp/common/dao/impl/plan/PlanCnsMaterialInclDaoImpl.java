package com.jnj.omp.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.plan.CnsMaterialInclEntity;

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
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_INCL,queryString,CnsMaterialInclEntity.class);

    }

    public List<CnsMaterialInclEntity> getAllEntity() {
        String queryString = QueryHelper.buildCriteria().toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_MATERIAL_INCL,queryString,CnsMaterialInclEntity.class);
    }

    public CnsMaterialInclEntity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        if (null!=localMaterialNumber && null!=localPlant){
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_INCL.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_MATERIAL_INCL.LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_INCL,queryString,CnsMaterialInclEntity.class);
        }
        return null;
    }
}
