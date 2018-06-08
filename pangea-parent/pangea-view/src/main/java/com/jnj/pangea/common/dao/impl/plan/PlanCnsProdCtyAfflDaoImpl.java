package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsProdCtyAfflEntity;

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
