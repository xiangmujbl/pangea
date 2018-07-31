package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanValEntity;
import java.util.List;
public class PlanCnsFinPlanValDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_FIN_PLAN_VAL = "/plan/cns_fin_plan_val";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String LOCAL_PLANT = "localPlant";
    public static final String SP_RELEVANT = "sPRelevant";
    public static final String NO_PLAN_RELEVANT = "noPlanRelevant";
    public static final String IDENTIFIER = "identifier";

    private static PlanCnsFinPlanValDaoImpl instance;

    public static PlanCnsFinPlanValDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsFinPlanValDaoImpl();
        }
        return instance;
    }

    public PlanCnsFinPlanValEntity getEntityWithConditions(String localMaterialNumber,String identifier) {
        if (null != localMaterialNumber && !"".equals(localMaterialNumber)){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IDENTIFIER).is(identifier).toQueryString();
            return queryForObject(PLAN_CNS_FIN_PLAN_VAL, queryString, PlanCnsFinPlanValEntity.class);
        }
        return null;
    }
    public List<PlanCnsFinPlanValEntity> getListWithConditions(String localMaterialNumber,String identifier) {
        if (null != localMaterialNumber && !"".equals(localMaterialNumber)){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IDENTIFIER).is(identifier).toQueryString();
            return queryForList(PLAN_CNS_FIN_PLAN_VAL, queryString, PlanCnsFinPlanValEntity.class);
        }
        return null;
    }
    public List<PlanCnsFinPlanValEntity> getListWithConditions(List<String> localMaterialNumber,String identifier) {
        if (null != localMaterialNumber && localMaterialNumber.size()>0){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).in(localMaterialNumber.toArray())
                    .and(IDENTIFIER).is(identifier).toQueryString();
            return queryForList(PLAN_CNS_FIN_PLAN_VAL, queryString, PlanCnsFinPlanValEntity.class);
        }
        return null;
    }
}
