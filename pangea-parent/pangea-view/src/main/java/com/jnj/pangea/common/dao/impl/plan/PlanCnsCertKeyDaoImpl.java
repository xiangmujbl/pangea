package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsCertKeyEntity;

public class PlanCnsCertKeyDaoImpl extends CommonDaoImpl {

    private static PlanCnsCertKeyDaoImpl instance;

    public static PlanCnsCertKeyDaoImpl getInstance(){
        if(null == instance){
            instance = new PlanCnsCertKeyDaoImpl();
        }
        return instance;
    }

    public PlanCnsCertKeyEntity getEntityByCertKey(String certKey) {

        return null;
    }
}
