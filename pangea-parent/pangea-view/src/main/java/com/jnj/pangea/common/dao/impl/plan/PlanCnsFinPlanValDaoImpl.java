package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanValEntity;
import java.util.List;
public class PlanCnsFinPlanValDaoImpl extends CommonDaoImpl {

    private static PlanCnsFinPlanValDaoImpl instance;

    public static PlanCnsFinPlanValDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsFinPlanValDaoImpl();
        }
        return instance;
    }

    public PlanCnsFinPlanValEntity getEntityWithConditions(String localMaterialNumber,String identifier) {
        if (null != localMaterialNumber && !"".equals(localMaterialNumber)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_FIN_PLAN_VAL.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.PLAN_CNS_FIN_PLAN_VAL.IDENTIFIER).is(identifier).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_FIN_PLAN_VAL, queryString, PlanCnsFinPlanValEntity.class);
        }
        return null;
    }
    public List<PlanCnsFinPlanValEntity> getListWithConditions(String localMaterialNumber,String identifier) {
        if (null != localMaterialNumber && !"".equals(localMaterialNumber)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_FIN_PLAN_VAL.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.PLAN_CNS_FIN_PLAN_VAL.IDENTIFIER).is(identifier).toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_FIN_PLAN_VAL, queryString, PlanCnsFinPlanValEntity.class);
        }
        return null;
    }

}
