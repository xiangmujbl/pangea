package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProcessTypeEntity extends CommonEntity {

    private String processTypeId;
    private String processTypeDescription;

    public PlanCnsProcessTypeEntity(Map<String, Object> map) {
        super(map);

        setProcessTypeId((String) map.get("processTypeId"));
        setProcessTypeDescription((String) map.get("processTypeDescription"));
    }

    public String getProcessTypeId() {
        return this.processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getProcessTypeDescription() {
        return this.processTypeDescription;
    }

    public void setProcessTypeDescription(String processTypeDescription) {
        this.processTypeDescription = processTypeDescription;
    }

}
