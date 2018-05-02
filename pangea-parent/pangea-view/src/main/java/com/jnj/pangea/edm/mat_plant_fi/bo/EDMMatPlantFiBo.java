package com.jnj.pangea.edm.mat_plant_fi.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMatPlantFiBo extends BaseBo{
    private String sourceSystem;
    private String localMaterialNumber;
    private String localPlant;
    private String localDeleIndicator;
    private String plant;
    private String priceControl;
    private String localStandardPrice;
    private String localPriceUnit;
    private String localMvp;

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

    public String getLocalDeleIndicator() {
        return localDeleIndicator;
    }

    public void setLocalDeleIndicator(String localDeleIndicator) {
        this.localDeleIndicator = localDeleIndicator;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPriceControl() {
        return priceControl;
    }

    public void setPriceControl(String priceControl) {
        this.priceControl = priceControl;
    }

    public String getLocalStandardPrice() {
        return localStandardPrice;
    }

    public void setLocalStandardPrice(String localStandardPrice) {
        this.localStandardPrice = localStandardPrice;
    }

    public String getLocalPriceUnit() {
        return localPriceUnit;
    }

    public void setLocalPriceUnit(String localPriceUnit) {
        this.localPriceUnit = localPriceUnit;
    }

    public String getLocalMvp() {
        return localMvp;
    }

    public void setLocalMvp(String localMvp) {
        this.localMvp = localMvp;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber",this.localMaterialNumber)
                .add("localPlant",this.localPlant)
                .toJsonString();
    }
}