package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsOrdRejEntity;

public class PlanCnsOrdRejDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_ORD_REJ = "/plan/cns_ord_rej";

    public static final String SALES_ORG = "salesOrg";
    public static final String REJ_CD = "rejCd";

    private static PlanCnsOrdRejDaoImpl instance;

    public static PlanCnsOrdRejDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsOrdRejDaoImpl();
        }
        return instance;
    }


    public PlanCnsOrdRejEntity getEntityWithSalesOrgAndRejCd(String salesOrg, String rejCd) {
        if (null != salesOrg && !"".equals(salesOrg) && null!=rejCd && !"".equals(rejCd)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(REJ_CD).is(rejCd).toQueryString();
            return queryForObject(PLAN_CNS_ORD_REJ, queryString, PlanCnsOrdRejEntity.class);
        }
        return null;
    }
}
