package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanQtyEntity;

import java.util.List;

public class PlanCnsFinPlanQtyDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_FIN_PLAN_QTY = "/plan/cns_fin_plan_qty";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String IDENTIFIER = "identifier";

    private static PlanCnsFinPlanQtyDaoImpl instance;

    public static PlanCnsFinPlanQtyDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsFinPlanQtyDaoImpl();
        }
        return instance;
    }

    public PlanCnsFinPlanQtyEntity getEntityWithConditions(String localMaterialNumber,String identifier) {
        if (null != localMaterialNumber && !"".equals(localMaterialNumber)){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IDENTIFIER).is(identifier).toQueryString();
            return queryForObject(PLAN_CNS_FIN_PLAN_QTY, queryString, PlanCnsFinPlanQtyEntity.class);
        }
        return null;
    }
    public List<PlanCnsFinPlanQtyEntity> getListWithConditions(String localMaterialNumber, String identifier) {
        if (null != localMaterialNumber && !"".equals(localMaterialNumber)){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IDENTIFIER).is(identifier).toQueryString();
            return queryForList(PLAN_CNS_FIN_PLAN_QTY, queryString, PlanCnsFinPlanQtyEntity.class);
        }
        return null;
    }
    public List<PlanCnsFinPlanQtyEntity> getListWithConditions(List<String> localMaterialNumber, String identifier) {
        if (null != localMaterialNumber && localMaterialNumber.size()>0){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).in(localMaterialNumber.toArray())
                    .and(IDENTIFIER).is(identifier).toQueryString();
            return queryForList(PLAN_CNS_FIN_PLAN_QTY, queryString, PlanCnsFinPlanQtyEntity.class);
        }
        return null;
    }
}
