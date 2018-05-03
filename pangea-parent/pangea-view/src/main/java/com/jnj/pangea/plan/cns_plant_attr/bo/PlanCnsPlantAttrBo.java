package com.jnj.pangea.plan.cns_plant_attr.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsPlantAttrBo extends BaseBo {

    private String sourceSystem;
    private String localPlant;
    private String localPlantName;
    private String localPlantType;
    private String plant;
    private String plantType;
    private String localPlanningRelevant;
    private String planLocTypeId;
    private String planLocTypeDesc;
    private String locationAttribute1Desc;
    private String locationAttribute1Value;
    private String locationAttribute2Desc;
    private String locationAttribute2Value;
    private String locationAttribute3Desc;
    private String locationAttribute3Value;
    private String locationAttribute4Desc;
    private String locationAttribute4Value;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalPlantName() {
        return localPlantName;
    }

    public void setLocalPlantName(String localPlantName) {
        this.localPlantName = localPlantName;
    }

    public String getLocalPlantType() {
        return localPlantType;
    }

    public void setLocalPlantType(String localPlantType) {
        this.localPlantType = localPlantType;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getLocalPlanningRelevant() {
        return localPlanningRelevant;
    }

    public void setLocalPlanningRelevant(String localPlanningRelevant) {
        this.localPlanningRelevant = localPlanningRelevant;
    }

    public String getPlanLocTypeId() {
        return planLocTypeId;
    }

    public void setPlanLocTypeId(String planLocTypeId) {
        this.planLocTypeId = planLocTypeId;
    }

    public String getPlanLocTypeDesc() {
        return planLocTypeDesc;
    }

    public void setPlanLocTypeDesc(String planLocTypeDesc) {
        this.planLocTypeDesc = planLocTypeDesc;
    }

    public String getLocationAttribute1Desc() {
        return locationAttribute1Desc;
    }

    public void setLocationAttribute1Desc(String locationAttribute1Desc) {
        this.locationAttribute1Desc = locationAttribute1Desc;
    }

    public String getLocationAttribute1Value() {
        return locationAttribute1Value;
    }

    public void setLocationAttribute1Value(String locationAttribute1Value) {
        this.locationAttribute1Value = locationAttribute1Value;
    }

    public String getLocationAttribute2Desc() {
        return locationAttribute2Desc;
    }

    public void setLocationAttribute2Desc(String locationAttribute2Desc) {
        this.locationAttribute2Desc = locationAttribute2Desc;
    }

    public String getLocationAttribute2Value() {
        return locationAttribute2Value;
    }

    public void setLocationAttribute2Value(String locationAttribute2Value) {
        this.locationAttribute2Value = locationAttribute2Value;
    }

    public String getLocationAttribute3Desc() {
        return locationAttribute3Desc;
    }

    public void setLocationAttribute3Desc(String locationAttribute3Desc) {
        this.locationAttribute3Desc = locationAttribute3Desc;
    }

    public String getLocationAttribute3Value() {
        return locationAttribute3Value;
    }

    public void setLocationAttribute3Value(String locationAttribute3Value) {
        this.locationAttribute3Value = locationAttribute3Value;
    }

    public String getLocationAttribute4Desc() {
        return locationAttribute4Desc;
    }

    public void setLocationAttribute4Desc(String locationAttribute4Desc) {
        this.locationAttribute4Desc = locationAttribute4Desc;
    }

    public String getLocationAttribute4Value() {
        return locationAttribute4Value;
    }

    public void setLocationAttribute4Value(String locationAttribute4Value) {
        this.locationAttribute4Value = locationAttribute4Value;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localPlant", this.localPlant)
                .toJsonString();
    }
}