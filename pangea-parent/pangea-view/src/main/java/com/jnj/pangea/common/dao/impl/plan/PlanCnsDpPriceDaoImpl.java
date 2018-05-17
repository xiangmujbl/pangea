package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPriceEntity;
import org.apache.commons.lang3.StringUtils;
import sun.swing.StringUIClientPropertyKey;

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

    public List<PlanCnsDpPriceEntity> getEntitiesWithLocalMaterialNumbers(String localMaterialNumbers) {
        if (StringUtils.isNotBlank(localMaterialNumbers)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DP_PRICE.LOCAL_MATERIAL_NUMBER).in(localMaterialNumbers).toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_DP_PRICE_CLONE, queryString, PlanCnsDpPriceEntity.class);

        }
        return null;
    }
}
