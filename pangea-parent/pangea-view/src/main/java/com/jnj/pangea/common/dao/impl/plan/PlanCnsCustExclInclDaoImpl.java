package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsCustExclInclEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsCustExclInclDaoImpl extends CommonDaoImpl {

    private static PlanCnsCustExclInclDaoImpl instance;

    public static PlanCnsCustExclInclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCustExclInclDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsCustExclInclEntity> getEntityListWithSalesOrgAndSourceSystem(String salesOrg, String sourceSystem){
        if (StringUtils.isNotEmpty(salesOrg)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CUST_EXCL_INCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_CUST_EXCL_INCL.SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_CUST_EXCL_INCL,queryString,PlanCnsCustExclInclEntity.class);
        }
        return null;
    }



}
