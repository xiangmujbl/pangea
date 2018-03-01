package com.jnj.pangea.view.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;

public class PlantBo extends BaseBo {

    private String sourceSystem;
    private String localPlant;
    private String localPlantName;
    private String plant;
    private String localPlanningRelevant;
    private String localCountry;
    private String country;
    private String site;
    private String localPlantType;
    private String plantType;
    private String localCurrency;
    private String region;

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

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getLocalPlanningRelevant() {
        return localPlanningRelevant;
    }

    public void setLocalPlanningRelevant(String localPlanningRelevant) {
        this.localPlanningRelevant = localPlanningRelevant;
    }

    public String getLocalCountry() {
        return localCountry;
    }

    public void setLocalCountry(String localCountry) {
        this.localCountry = localCountry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLocalPlantType() {
        return localPlantType;
    }

    public void setLocalPlantType(String localPlantType) {
        this.localPlantType = localPlantType;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localPlant", this.localPlant)
                .toJsonString();
    }
}
