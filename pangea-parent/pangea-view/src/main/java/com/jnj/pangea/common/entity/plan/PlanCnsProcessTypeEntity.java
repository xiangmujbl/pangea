package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProcessTypeEntity extends CommonEntity {

    private String processTypeId;
    private String processTypeDesc;

    public PlanCnsProcessTypeEntity(Map<String, Object> map) {
        super(map);

        setProcessTypeId((String) map.get("processTypeId"));
        setProcessTypeDesc((String) map.get("processTypeDesc"));
    }

    public String getProcessTypeId() {
        return this.processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getProcessTypeDesc() {
        return this.processTypeDesc;
    }

    public void setProcessTypeDesc(String processTypeDesc) {
        this.processTypeDesc = processTypeDesc;
    }

}
