package com.jnj.omp.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.plan.PlanCnsDpPriceEntity;

import java.util.List;

public class PlanCnsDpPriceDaoImpl extends CommonDaoImpl {

    private static PlanCnsDpPriceDaoImpl instance;

    public static PlanCnsDpPriceDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDpPriceDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsDpPriceEntity> getEntitiesWithLocalMaterialNumbers(List<String> localMaterialNumbers) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DP_PRICE.LOCAL_MATERIAL_NUMBER).in(localMaterialNumbers).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_DP_PRICE_CLONE, queryString, PlanCnsDpPriceEntity.class);
    }
}
