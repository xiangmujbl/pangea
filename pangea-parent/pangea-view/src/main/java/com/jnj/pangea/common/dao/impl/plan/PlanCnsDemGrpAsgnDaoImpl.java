package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;

public class PlanCnsDemGrpAsgnDaoImpl extends CommonDaoImpl {

    private static PlanCnsDemGrpAsgnDaoImpl instance;

    public static PlanCnsDemGrpAsgnDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDemGrpAsgnDaoImpl();
        }
        return instance;
    }

    public PlanCnsDemGrpAsgnEntity getEntityWithCustomerShipTo(String customerShipTo) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP_ASGN.CUSTOMER_SHIP_TO).is(customerShipTo).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
    }
}
