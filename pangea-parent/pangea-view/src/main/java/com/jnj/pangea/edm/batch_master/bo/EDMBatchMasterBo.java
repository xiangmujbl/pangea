package com.jnj.pangea.edm.batch_master.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMBatchMasterBo extends BaseBo {

    private String sourceSystem;
    private String localMaterialNumber;
    private String localBatchNumber;
    private String localPlant;
    private String localStorageLocation;
    private String localBatchExpDate;
    private String localBatchMfgDate;
    private String plant;
    private String materialNumber;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber", this.localMaterialNumber)
                .add("localBatchNumber", this.localBatchNumber)
                .add("localPlant", this.localPlant)
                .add("localStorageLocation", this.localStorageLocation)
                .toJsonString();
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

    public String getLocalBatchNumber() {
        return this.localBatchNumber;
    }

    public void setLocalBatchNumber(String localBatchNumber) {
        this.localBatchNumber = localBatchNumber;
    }

    public String getLocalPlant() {
        return this.localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalStorageLocation() {
        return this.localStorageLocation;
    }

    public void setLocalStorageLocation(String localStorageLocation) {
        this.localStorageLocation = localStorageLocation;
    }

    public String getLocalBatchExpDate() {
        return this.localBatchExpDate;
    }

    public void setLocalBatchExpDate(String localBatchExpDate) {
        this.localBatchExpDate = localBatchExpDate;
    }

    public String getLocalBatchMfgDate() {
        return this.localBatchMfgDate;
    }

    public void setLocalBatchMfgDate(String localBatchMfgDate) {
        this.localBatchMfgDate = localBatchMfgDate;
    }

    public String getPlant() {
        return this.plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getMaterialNumber() {
        return this.materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

}
