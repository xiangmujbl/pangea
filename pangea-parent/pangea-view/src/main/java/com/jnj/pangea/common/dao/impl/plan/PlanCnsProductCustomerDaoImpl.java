package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsProductCustomerEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class PlanCnsProductCustomerDaoImpl extends CommonDaoImpl {
    private static PlanCnsProductCustomerDaoImpl instance;

    public static PlanCnsProductCustomerDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProductCustomerDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsProductCustomerEntity> getListWithProductIdAndSourceSystem(String productId, String sourceSystem){
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PRODUCT_CUSTOMER.PRODUCT_ID).is(productId)
                .and(IConstant.PLAN_CNS_PRODUCT_CUSTOMER.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        LogUtil.getCoreLog().info(queryString);
        return queryForList(IConstant.REGION.PLAN_CNS_PRODUCTCUSTOMER, queryString, PlanCnsProductCustomerEntity.class);
    }
}
