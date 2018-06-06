package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPosEntity;

import java.util.List;

public class PlanCnsDpPosDaoImpl extends CommonDaoImpl {

    private static PlanCnsDpPosDaoImpl instance;

    public static PlanCnsDpPosDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDpPosDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsDpPosEntity> getEntityListWithLocalMaterial(List localMaterialList) {
        if (null != localMaterialList && localMaterialList.size() > 0) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DP_POS.LOCAL_MATERIAL).in(localMaterialList).toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_DP_POS, queryString, PlanCnsDpPosEntity.class);
        }
        return null;
    }
}
