package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMaterialPlantFinV1Entity extends CommonEntity {

    private String localMaterialNumber;
    private String localPlant;
    private String priceControl;
    private String localStandardPrice;
    private String localPriceUnit;
    private String localMovingAverage;
    private String localPriceControlIndicator;
    private String localMvp;

    public EDMMaterialPlantFinV1Entity(Map<String, Object> map) {
        super(map);

        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalPlant((String) map.get("localPlant"));
        setPriceControl((String) map.get("priceControl"));
        setLocalStandardPrice((String) map.get("localStandardPrice"));
        setLocalPriceUnit((String) map.get("localPriceUnit"));
        setLocalMovingAverage((String) map.get("localMovingAverage"));
        setLocalPriceControlIndicator((String) map.get("localPriceControlIndicator"));
        setLocalMvp((String)map.get("localMvp"));
    }

    public String getLocalMvp() {
        return localMvp;
    }

    public void setLocalMvp(String localMvp) {
        this.localMvp = localMvp;
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

    public String getPriceControl() {
        return this.priceControl;
    }

    public void setPriceControl(String priceControl) {
        this.priceControl = priceControl;
    }

    public String getLocalStandardPrice() {
        return this.localStandardPrice;
    }

    public void setLocalStandardPrice(String localStandardPrice) {
        this.localStandardPrice = localStandardPrice;
    }

    public String getLocalPriceUnit() {
        return this.localPriceUnit;
    }

    public void setLocalPriceUnit(String localPriceUnit) {
        this.localPriceUnit = localPriceUnit;
    }

    public String getLocalMovingAverage() {
        return this.localMovingAverage;
    }

    public void setLocalMovingAverage(String localMovingAverage) {
        this.localMovingAverage = localMovingAverage;
    }

    public String getLocalPriceControlIndicator() {
        return this.localPriceControlIndicator;
    }

    public void setLocalPriceControlIndicator(String localPriceControlIndicator) {
        this.localPriceControlIndicator = localPriceControlIndicator;
    }


}
