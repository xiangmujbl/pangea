package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsCertKeyEntity extends CommonEntity {

    private String certainityKey;
    private String certainityKeyDesc;

    public PlanCnsCertKeyEntity(Map<String, Object> map) {
        super(map);

        setCertainityKey((String) map.get("certainityKey"));
        setCertainityKeyDesc((String) map.get("certainityKeyDesc"));
    }

    public String getCertainityKey() {
        return this.certainityKey;
    }

    public void setCertainityKey(String certainityKey) {
        this.certainityKey = certainityKey;
    }

    public String getCertainityKeyDesc() {
        return this.certainityKeyDesc;
    }

    public void setCertainityKeyDesc(String certainityKeyDesc) {
        this.certainityKeyDesc = certainityKeyDesc;
    }

}
