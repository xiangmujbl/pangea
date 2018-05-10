package com.jnj.omp.common.entity.edm;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EDMBatchMasterV1Entity extends CommonEntity {

    private String sourceSystem;
    private String localMaterialNumber;
    private String materialNumber;
    private String localBatchNumber;
    private String localShelfLifeExpiration;
    private String localDateofManufacture;

    public EDMBatchMasterV1Entity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setMaterialNumber((String) map.get("materialNumber"));
        setLocalBatchNumber((String) map.get("localBatchNumber"));
        setLocalShelfLifeExpiration((String) map.get("localShelfLifeExpiration"));
        setLocalDateofManufacture((String) map.get("localDateofManufacture"));
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        localMaterialNumber = localMaterialNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        materialNumber = materialNumber;
    }

    public String getLocalBatchNumber() {
        return localBatchNumber;
    }

    public void setLocalBatchNumber(String localBatchNumber) {
        localBatchNumber = localBatchNumber;
    }

    public String getLocalShelfLifeExpiration() {
        return localShelfLifeExpiration;
    }

    public void setLocalShelfLifeExpiration(String localShelfLifeExpiration) {
        localShelfLifeExpiration = localShelfLifeExpiration;
    }

    public String getLocalDateofManufacture() {
        return localDateofManufacture;
    }

    public void setLocalDateofManufacture(String localDateofManufacture) {
        localDateofManufacture = localDateofManufacture;
    }

}
