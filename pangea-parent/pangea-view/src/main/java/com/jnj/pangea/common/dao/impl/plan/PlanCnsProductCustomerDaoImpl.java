package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsProductCustomerEntity;

import java.util.List;

public class PlanCnsProductCustomerDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PRODUCT_CUSTOMER = "/plan/cns_productcustomer";
    public static final String PLAN_CNS_PRODUCT_CUSTOMER_CLONE = "/plan/cns_productcustomer_clone";

    public static final String CUSTOMER_ID = "customerId";
    public static final String PRODUCT_ID = "productId";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String DEMAND_GROUP = "demandGroup";
    public static final String PRODUCT_STATUS = "productStatus";
    public static final String MIN_STOCK = "minStock";
    public static final String MAX_STOCK = "maxStock";
    public static final String STOCK_LEVEL = "stockLevel";
    public static final String UOM = "uom";
    public static final String PREFERRED_DC = "preferredDC";
    public static final String LEAD_TIME = "leadTime";
    public static final String REVENUE_RECOGNITION_OFFSET = "revenueRecognitionOffset";

    private static PlanCnsProductCustomerDaoImpl instance;

    public static PlanCnsProductCustomerDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProductCustomerDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsProductCustomerEntity> getListWithProductIdAndSourceSystem(String productId, String sourceSystem){
        String queryString = QueryHelper.buildCriteria(PRODUCT_ID).is(productId)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        LogUtil.getCoreLog().info(queryString);
        return queryForList(PLAN_CNS_PRODUCT_CUSTOMER, queryString, PlanCnsProductCustomerEntity.class);
    }
    public List<PlanCnsProductCustomerEntity> getListWithProductIdAndSourceSystemClone(String productId, String sourceSystem){
        String localMaterialNumber = productId.replaceAll("^(0+)", "");
        String queryString = QueryHelper.buildCriteria(PRODUCT_ID).is(localMaterialNumber)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        LogUtil.getCoreLog().info(queryString);
        return queryForList(PLAN_CNS_PRODUCT_CUSTOMER_CLONE, queryString, PlanCnsProductCustomerEntity.class);
    }

    public PlanCnsProductCustomerEntity getEntityWithDemandGroup(String demandGroup) {

        String queryString = QueryHelper.buildCriteria(CUSTOMER_ID).is(demandGroup).toQueryString();
        return queryForObject(PLAN_CNS_PRODUCT_CUSTOMER, queryString, PlanCnsProductCustomerEntity.class);
    }
}
