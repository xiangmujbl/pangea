package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsCustExclInclEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsCustExclInclDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_CUST_EXCL_INCL = "/plan/cns_cust_excl_incl";

    public static final String COUNTRY = "country";
    public static final String SALES_ORG = "salesOrg";
    public static final String CUSTOMER_SHIP_TO = "customerShipTo";
    public static final String INCL_EXCL = "inclExcl";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static PlanCnsCustExclInclDaoImpl instance;

    public static PlanCnsCustExclInclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCustExclInclDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsCustExclInclEntity> getEntityListWithSalesOrgAndSourceSystem(String salesOrg, String sourceSystem){
        if (StringUtils.isNotEmpty(salesOrg)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(PLAN_CNS_CUST_EXCL_INCL,queryString,PlanCnsCustExclInclEntity.class);
        }
        return null;
    }



}
