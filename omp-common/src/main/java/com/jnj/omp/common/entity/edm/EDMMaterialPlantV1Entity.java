package com.jnj.omp.common.entity.edm;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EDMMaterialPlantV1Entity extends CommonEntity{

    private String localMaterialNumber;
    private String localPlant;
    private String materialNumber;
    private String localDeletionFlagPlant;
    private String localMrpType;
    private String localMaterialType;

    public EDMMaterialPlantV1Entity(Map<String, Object> map) {
        super(map);
        setLocalMaterialNumber((String)map.get("localMaterialNumber"));
        setLocalPlant((String)map.get("localPlant"));
        setMaterialNumber((String)map.get("materialNumber"));
        setLocalDeletionFlagPlant((String)map.get("localDeletionFlagPlant"));
        setLocalMrpType((String)map.get("localMrpType"));
        setLocalMaterialType((String)map.get("localMaterialType"));
    }

    public String getLocalDeletionFlagPlant() {
        return localDeletionFlagPlant;
    }

    public void setLocalDeletionFlagPlant(String localDeletionFlagPlant) {
        this.localDeletionFlagPlant = localDeletionFlagPlant;
    }

    public String getLocalMrpType() {
        return localMrpType;
    }

    public void setLocalMrpType(String localMrpType) {
        this.localMrpType = localMrpType;
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

    public String getLocalMaterialType() {
        return localMaterialType;
    }

    public void setLocalMaterialType(String localMaterialType) {
        this.localMaterialType = localMaterialType;
    }
}
