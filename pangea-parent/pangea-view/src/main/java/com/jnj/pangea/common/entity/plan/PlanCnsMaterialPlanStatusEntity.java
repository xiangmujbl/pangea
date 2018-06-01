package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsMaterialPlanStatusEntity extends CommonEntity {

    private String sourceSystem;
    private String localMaterialNumber;
    private String localPlant;
    private String materialNumber;
    private String localParentCode;
    private String ppc;
    private String active;
    private String dpRelevant;
    private String spRelevant;
    private String parentActive;
    private String noPlanRelevant;

    public PlanCnsMaterialPlanStatusEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalPlant((String) map.get("localPlant"));
        setMaterialNumber((String) map.get("materialNumber"));
        setLocalParentCode((String) map.get("localParentCode"));
        setPpc((String) map.get("ppc"));
        setActive((String) map.get("active"));
        setDpRelevant((String) map.get("dpRelevant"));
        setSpRelevant((String) map.get("spRelevant"));
        setParentActive((String) map.get("parentActive"));
        setNoPlanRelevant((String) map.get("noPlanRelevant"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalPlant() {
        return this.localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getMaterialNumber() {
        return this.materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLocalParentCode() {
        return this.localParentCode;
    }

    public void setLocalParentCode(String localParentCode) {
        this.localParentCode = localParentCode;
    }

    public String getPpc() {
        return this.ppc;
    }

    public void setPpc(String ppc) {
        this.ppc = ppc;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDpRelevant() {
        return this.dpRelevant;
    }

    public void setDpRelevant(String dpRelevant) {
        this.dpRelevant = dpRelevant;
    }

    public String getSpRelevant() {
        return this.spRelevant;
    }

    public void setSpRelevant(String spRelevant) {
        this.spRelevant = spRelevant;
    }

    public String getParentActive() {
        return this.parentActive;
    }

    public void setParentActive(String parentActive) {
        this.parentActive = parentActive;
    }

    public String getNoPlanRelevant() {
        return this.noPlanRelevant;
    }

    public void setNoPlanRelevant(String noPlanRelevant) {
        this.noPlanRelevant = noPlanRelevant;
    }

    @Override
    public String toString() {
        return "PlanCnsMaterialPlanStatusEntity{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", localMaterialNumber='" + localMaterialNumber + '\'' +
                ", localPlant='" + localPlant + '\'' +
                ", materialNumber='" + materialNumber + '\'' +
                ", localParentCode='" + localParentCode + '\'' +
                ", ppc='" + ppc + '\'' +
                ", active='" + active + '\'' +
                ", dpRelevant='" + dpRelevant + '\'' +
                ", spRelevant='" + spRelevant + '\'' +
                ", parentActive='" + parentActive + '\'' +
                ", noPlanRelevant='" + noPlanRelevant + '\'' +
                '}';
    }
}
