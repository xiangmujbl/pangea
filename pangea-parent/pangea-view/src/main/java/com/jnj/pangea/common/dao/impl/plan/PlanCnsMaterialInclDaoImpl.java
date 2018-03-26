package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.pangea.cns_material_plan_status.bo.CnsMaterialInclBo;

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
}
