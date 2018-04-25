package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsSplProcTypEntity extends CommonEntity {

    private String specialProcurementType;
    private String splProcType;
    private String localSplProcType;
    private String sourceSystem;

    public PlanCnsSplProcTypEntity(Map<String, Object> map) {
        super(map);

        setSpecialProcurementType((String) map.get("specialProcurementType"));
        setSplProcType((String) map.get("splProcType"));
        setLocalSplProcType((String) map.get("localSplProcType"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getSpecialProcurementType() {
        return this.specialProcurementType;
    }

    public void setSpecialProcurementType(String specialProcurementType) {
        this.specialProcurementType = specialProcurementType;
    }

    public String getSplProcType() {
        return this.splProcType;
    }

    public void setSplProcType(String splProcType) {
        this.splProcType = splProcType;
    }

    public String getLocalSplProcType() {
        return this.localSplProcType;
    }

    public void setLocalSplProcType(String localSplProcType) {
        this.localSplProcType = localSplProcType;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

}
