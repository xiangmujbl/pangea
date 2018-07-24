package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPosEntity;

import java.util.List;

public class PlanCnsDpPosDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_DP_POS = "/plan/cns_dp_pos";

    public static final String LOCAL_MATERIAL = "localMaterial";

    private static PlanCnsDpPosDaoImpl instance;

    public static PlanCnsDpPosDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDpPosDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsDpPosEntity> getEntityListWithLocalMaterial(List localMaterialList) {
        if (null != localMaterialList && localMaterialList.size() > 0) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL).in(localMaterialList).toQueryString();
            //LogUtil.getCoreLog().info("================ getEntityListWithLocalMaterial queryString=" + queryString);
            return queryForList(PLAN_CNS_DP_POS, queryString, PlanCnsDpPosEntity.class);
        }
        return null;
    }
}
