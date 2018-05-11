package com.jnj.pangea.plan.cns_material_plan_status_3.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsMaterialPlanStatusBo extends BaseBo {

    private String sourceSystem ;
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

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLocalParentCode() {
        return localParentCode;
    }

    public void setLocalParentCode(String localParentCode) {
        this.localParentCode = localParentCode;
    }

    public String getPpc() {
        return ppc;
    }

    public void setPpc(String ppc) {
        this.ppc = ppc;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDpRelevant() {
        return dpRelevant;
    }

    public void setDpRelevant(String dpRelevant) {
        this.dpRelevant = dpRelevant;
    }

    public String getSpRelevant() {
        return spRelevant;
    }

    public void setSpRelevant(String spRelevant) {
        this.spRelevant = spRelevant;
    }

    public String getParentActive() {
        return parentActive;
    }

    public void setParentActive(String parentActive) {
        this.parentActive = parentActive;
    }

    public String getNoPlanRelevant() {
        return noPlanRelevant;
    }

    public void setNoPlanRelevant(String noPlanRelevant) {
        this.noPlanRelevant = noPlanRelevant;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber", this.localMaterialNumber)
                .add("localPlant", this.localPlant)
                .toJsonString();
    }
}