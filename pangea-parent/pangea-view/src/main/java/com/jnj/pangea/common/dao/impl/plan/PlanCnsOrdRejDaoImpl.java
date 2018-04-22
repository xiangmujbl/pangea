package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsOrdRejEntity;

public class PlanCnsOrdRejDaoImpl extends CommonDaoImpl {

    private static PlanCnsOrdRejDaoImpl instance;

    public static PlanCnsOrdRejDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsOrdRejDaoImpl();
        }
        return instance;
    }

    public PlanCnsOrdRejEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public PlanCnsOrdRejEntity getEntityWithSalesOrgAndRejCd(String salesOrg, String rejCd) {
        if (null != salesOrg && !"".equals(salesOrg) && null!=rejCd && !"".equals(rejCd)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_ORD_REJ.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_ORD_REJ.REJ_CD).is(rejCd).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_ORD_REJ, queryString, PlanCnsOrdRejEntity.class);
        }
        return null;
    }
}
