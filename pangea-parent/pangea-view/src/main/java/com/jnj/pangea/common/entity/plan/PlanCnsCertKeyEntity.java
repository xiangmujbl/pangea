package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsCertKeyEntity extends CommonEntity {

    private String certaintyKey;
    private String certaintyKeyDesc;

    public PlanCnsCertKeyEntity(Map<String, Object> map) {
        super(map);
        setCertaintyKey((String) map.get("certaintyKey"));
        setCertaintyKeyDesc((String) map.get("certaintyKeyDesc"));
    }

    public String getCertaintyKey() {
        return certaintyKey;
    }

    public void setCertaintyKey(String certaintyKey) {
        this.certaintyKey = certaintyKey;
    }

    public String getCertaintyKeyDesc() {
        return certaintyKeyDesc;
    }

    public void setCertaintyKeyDesc(String certaintyKeyDesc) {
        this.certaintyKeyDesc = certaintyKeyDesc;
    }
}
