package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

public class PlanCnsSoTypeInclDaoImpl extends CommonDaoImpl {
    private static PlanCnsSoTypeInclDaoImpl instance;

    public static PlanCnsSoTypeInclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsSoTypeInclDaoImpl();
        }
        return instance;
    }
}
