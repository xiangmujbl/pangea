package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsProdCtyAfflEntity;

import java.util.List;

public class PlanCnsProdCtyAfflDaoImpl  extends CommonDaoImpl {
    private static PlanCnsProdCtyAfflDaoImpl instance;

    public static PlanCnsProdCtyAfflDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProdCtyAfflDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsProdCtyAfflEntity> getEntitiesAll() {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PROD_CTY_AFFL.SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();
        LogUtil.getCoreLog().info("queryString:"+queryString);
        return queryForList(IConstant.REGION.PLAN_CNS_PROD_CTY_AFFL,"*:*", PlanCnsProdCtyAfflEntity.class);
    }
}
