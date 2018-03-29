package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlantAttrEntity extends CommonEntity {

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

    public PlanCnsPlantAttrEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalPlant((String) map.get("localPlant"));
        setLocalPlantName((String) map.get("localPlantName"));
        setLocalPlantType((String) map.get("localPlantType"));
        setPlant((String) map.get("plant"));
        setPlantType((String) map.get("plantType"));
        setLocalPlanningRelevant((String) map.get("localPlanningRelevant"));
        setPlanLocTypeId((String) map.get("planLocTypeId"));
        setPlanLocTypeDesc((String) map.get("planLocTypeDesc"));
        setLocationAttribute1Desc((String) map.get("locationAttribute1Desc"));
        setLocationAttribute1Value((String) map.get("locationAttribute1Value"));
        setLocationAttribute2Desc((String) map.get("locationAttribute2Desc"));
        setLocationAttribute2Value((String) map.get("locationAttribute2Value"));
        setLocationAttribute3Desc((String) map.get("locationAttribute3Desc"));
        setLocationAttribute3Value((String) map.get("locationAttribute3Value"));
        setLocationAttribute4Desc((String) map.get("locationAttribute4Desc"));
        setLocationAttribute4Value((String) map.get("locationAttribute4Value"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalPlant() {
        return this.localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalPlantName() {
        return this.localPlantName;
    }

    public void setLocalPlantName(String localPlantName) {
        this.localPlantName = localPlantName;
    }

    public String getLocalPlantType() {
        return this.localPlantType;
    }

    public void setLocalPlantType(String localPlantType) {
        this.localPlantType = localPlantType;
    }

    public String getPlant() {
        return this.plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPlantType() {
        return this.plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getLocalPlanningRelevant() {
        return this.localPlanningRelevant;
    }

    public void setLocalPlanningRelevant(String localPlanningRelevant) {
        this.localPlanningRelevant = localPlanningRelevant;
    }

    public String getPlanLocTypeId() {
        return this.planLocTypeId;
    }

    public void setPlanLocTypeId(String planLocTypeId) {
        this.planLocTypeId = planLocTypeId;
    }

    public String getPlanLocTypeDesc() {
        return this.planLocTypeDesc;
    }

    public void setPlanLocTypeDesc(String planLocTypeDesc) {
        this.planLocTypeDesc = planLocTypeDesc;
    }

    public String getLocationAttribute1Desc() {
        return this.locationAttribute1Desc;
    }

    public void setLocationAttribute1Desc(String locationAttribute1Desc) {
        this.locationAttribute1Desc = locationAttribute1Desc;
    }

    public String getLocationAttribute1Value() {
        return this.locationAttribute1Value;
    }

    public void setLocationAttribute1Value(String locationAttribute1Value) {
        this.locationAttribute1Value = locationAttribute1Value;
    }

    public String getLocationAttribute2Desc() {
        return this.locationAttribute2Desc;
    }

    public void setLocationAttribute2Desc(String locationAttribute2Desc) {
        this.locationAttribute2Desc = locationAttribute2Desc;
    }

    public String getLocationAttribute2Value() {
        return this.locationAttribute2Value;
    }

    public void setLocationAttribute2Value(String locationAttribute2Value) {
        this.locationAttribute2Value = locationAttribute2Value;
    }

    public String getLocationAttribute3Desc() {
        return this.locationAttribute3Desc;
    }

    public void setLocationAttribute3Desc(String locationAttribute3Desc) {
        this.locationAttribute3Desc = locationAttribute3Desc;
    }

    public String getLocationAttribute3Value() {
        return this.locationAttribute3Value;
    }

    public void setLocationAttribute3Value(String locationAttribute3Value) {
        this.locationAttribute3Value = locationAttribute3Value;
    }

    public String getLocationAttribute4Desc() {
        return this.locationAttribute4Desc;
    }

    public void setLocationAttribute4Desc(String locationAttribute4Desc) {
        this.locationAttribute4Desc = locationAttribute4Desc;
    }

    public String getLocationAttribute4Value() {
        return this.locationAttribute4Value;
    }

    public void setLocationAttribute4Value(String locationAttribute4Value) {
        this.locationAttribute4Value = locationAttribute4Value;
    }

}
