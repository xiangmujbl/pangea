package com.jnj.omp.common.dao.impl.plan;

import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.plan.PlanCnsProdCtyAfflEntity;

import java.util.List;

public class PlanCnsProdCtyAfflDaoImpl  extends CommonDaoImpl {
    private static PlanCnsProdCtyAfflDaoImpl instance;

    public static PlanCnsProdCtyAfflDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProdCtyAfflDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsProdCtyAfflEntity> getEntitiesAll() {
        return queryForList(IConstant.REGION.PLAN_CNS_PROD_CTY_AFFL,"*:*", PlanCnsProdCtyAfflEntity.class);
    }
}
