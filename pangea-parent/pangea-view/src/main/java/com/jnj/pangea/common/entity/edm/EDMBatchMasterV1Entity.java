package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMBatchMasterV1Entity extends CommonEntity {

    private String sourceSystem;
    private String localMaterialNumber;
    private String materialNumber;
    private String localShelfLifeExpiration;
    private String localDateofManufacture;

    private String btchExpDt;
    private String btchMfgDt;
    private String srcSysCd;
    private String btchNum;
    private String matlNum;
    private String localPlant;


    public EDMBatchMasterV1Entity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setMaterialNumber((String) map.get("materialNumber"));
        setLocalShelfLifeExpiration((String) map.get("localShelfLifeExpiration"));
        setLocalDateofManufacture((String) map.get("localDateofManufacture"));
        setBtchExpDt((String) map.get("btchExpDt"));
        setBtchMfgDt((String) map.get("btchMfgDt"));
        setSrcSysCd((String) map.get("srcSysCd"));
        setMatlNum((String) map.get("matlNum"));
        setLocalPlant((String) map.get("localPlant"));
        setBtchNum((String) map.get("btchNum"));
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



    public String getBtchNum() {
        return btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
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

    public String getBtchExpDt() {
        return btchExpDt;
    }

    public void setBtchExpDt(String btchExpDt) {
        this.btchExpDt = btchExpDt;
    }


    public String getBtchMfgDt() {
        return btchMfgDt;
    }

    public void setBtchMfgDt(String btchMfgDt) {
        this.btchMfgDt = btchMfgDt;
    }


    public String getMatlNum() {
        return matlNum;
    }
    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }
    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

}
