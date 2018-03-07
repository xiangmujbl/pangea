package com.jnj.pangea.edm.material.plant.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMaterialPlantBo  extends BaseBo
{
    private String sourceSystem;
    private String localMaterialNumber;
    private String localPlant;
    private String materialNumber;
    private String plant;
    private String localPlantStatus;
    private String plantStatus;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber", this.localMaterialNumber)
                .add("localPlant", this.localPlant)
                .toJsonString();
    }

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

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getLocalPlantStatus() {
        return localPlantStatus;
    }

    public void setLocalPlantStatus(String localPlantStatus) {
        this.localPlantStatus = localPlantStatus;
    }

    public String getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }
}
