package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProcTypeEntity extends CommonEntity {

    private String procurementType;
    private String localProcurementType;
    private String sourceSystem;

    public PlanCnsProcTypeEntity(Map<String, Object> map) {
        super(map);

        setProcurementType((String) map.get("procurementType"));
        setLocalProcurementType((String) map.get("localProcurementType"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getProcurementType() {
        return this.procurementType;
    }

    public void setProcurementType(String procurementType) {
        this.procurementType = procurementType;
    }

    public String getLocalProcurementType() {
        return this.localProcurementType;
    }

    public void setLocalProcurementType(String localProcurementType) {
        this.localProcurementType = localProcurementType;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

}
