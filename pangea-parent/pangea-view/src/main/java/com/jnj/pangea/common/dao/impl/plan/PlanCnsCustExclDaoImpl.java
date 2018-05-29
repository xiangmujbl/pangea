package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsCustExclEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsCustExclDaoImpl extends CommonDaoImpl {

    private static PlanCnsCustExclDaoImpl instance;

    public static PlanCnsCustExclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCustExclDaoImpl();
        }
        return instance;
    }

    public PlanCnsCustExclEntity getEntityWithSalesOrgAndCustomerShipTo(String salesOrg, String customerShipTo){
        if (!salesOrg.isEmpty() && !customerShipTo.isEmpty()){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CUST_EXCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_CUST_EXCL.CUSTOMER_SHIP_TO).is(customerShipTo).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_CUST_EXCL,queryString,PlanCnsCustExclEntity.class);
        }
        return null;
    }
    public PlanCnsCustExclEntity getEntityWithSalesOrgAndCustomerShipTo(String salesOrg){
        if (!salesOrg.isEmpty()){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CUST_EXCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_CUST_EXCL.CUSTOMER_SHIP_TO).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_CUST_EXCL,queryString,PlanCnsCustExclEntity.class);
        }
        return null;
    }

    public PlanCnsCustExclEntity getEntityWithSalesOrgAndNotCustomerShipTo(String salesOrg, String customerShipTo){
        if (StringUtils.isNotEmpty(salesOrg)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CUST_EXCL.SALES_ORG).is(salesOrg).toQueryString();
            List<PlanCnsCustExclEntity> custExclEntityList = queryForList(IConstant.REGION.PLAN_CNS_CUST_EXCL,queryString,PlanCnsCustExclEntity.class);
            if (StringUtils.isNotEmpty(customerShipTo)){
                for (PlanCnsCustExclEntity custExclEntity:custExclEntityList) {
                    if(customerShipTo.equals(custExclEntity.getCustomerShipTo())){
                        return custExclEntity;
                    }
                }
            }
        }
        return null;
    }
}
