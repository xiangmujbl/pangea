package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsProcessTypeEntity extends CommonEntity {

    private String processTypeId;
    private String processTypeDesc;

    public CnsProcessTypeEntity (Map<String, Object> map) {
        super(map);
        setProcessTypeId((String) map.get("processTypeId"));
        setProcessTypeDesc((String) map.get("processTypeDesc"));
    }

    public String getProcessTypeId () {
        return processTypeId;
    }

    public void setProcessTypeId (String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getProcessTypeDesc () {
        return processTypeDesc;
    }

    public void setProcessTypeDesc (String processTypeDesc) {
        this.processTypeDesc = processTypeDesc;
    }
}
