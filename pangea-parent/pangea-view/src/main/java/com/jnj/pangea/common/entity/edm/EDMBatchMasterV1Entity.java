package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMBatchMasterV1Entity extends CommonEntity {

    private String sourceSystem;
    private String localMaterialNumber;
    private String materialNumber;
    private String localBatchNumber;
    private String localShelfLifeExpiration;
    private String localDateofManufacture;
    private String localBatchExpDate;
    private String localBatchMfgDate;
    private String srcSysCd;
    private String matlId;
    private String locPrtyId;


    public EDMBatchMasterV1Entity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setMaterialNumber((String) map.get("materialNumber"));
        setLocalBatchNumber((String) map.get("localBatchNumber"));
        setLocalShelfLifeExpiration((String) map.get("localShelfLifeExpiration"));
        setLocalDateofManufacture((String) map.get("localDateofManufacture"));
        setLocalBatchExpDate((String) map.get("localBatchExpDate"));
        setLocalBatchMfgDate((String) map.get("localBatchMfgDate"));
        setSrcSysCd((String) map.get("srcSysCd"));
        setMatlId((String) map.get("matlId"));
        setLocPrtyId((String) map.get("locPrtyId"));
    }

    public String getSrcSysCd() { return srcSysCd; }

    public void setSrcSysCd(String srcSysCd) { this.srcSysCd = srcSysCd; }

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

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLocalBatchNumber() {
        return localBatchNumber;
    }

    public void setLocalBatchNumber(String localBatchNumber) {
        this.localBatchNumber = localBatchNumber;
    }

    public String getLocalShelfLifeExpiration() {
        return localShelfLifeExpiration;
    }

    public void setLocalShelfLifeExpiration(String localShelfLifeExpiration) {
        this.localShelfLifeExpiration = localShelfLifeExpiration;
    }

    public String getLocalDateofManufacture() {
        return localDateofManufacture;
    }

    public void setLocalDateofManufacture(String localDateofManufacture) {
        this.localDateofManufacture = localDateofManufacture;
    }

    public String getLocalBatchExpDate() {
        return localBatchExpDate;
    }

    public void setLocalBatchExpDate(String localBatchExpDate) {
        this.localBatchExpDate = localBatchExpDate;
    }

    public String getLocalBatchMfgDate() {
        return localBatchMfgDate;
    }

    public void setLocalBatchMfgDate(String localBatchMfgDate) {
        this.localBatchMfgDate = localBatchMfgDate;
    }

    public String getMatlId() { return matlId; }

    public void setMatlId(String matlId) { this.matlId = matlId; }

    public String getLocPrtyId() { return locPrtyId; }

    public void setLocPrtyId(String locPrtyId) { this.locPrtyId = locPrtyId; }
}
