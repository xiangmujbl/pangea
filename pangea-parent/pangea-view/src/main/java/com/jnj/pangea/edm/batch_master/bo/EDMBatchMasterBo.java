package com.jnj.pangea.edm.batch_master.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMBatchMasterBo extends BaseBo {

    private String srcSysCd;
    private String matlNum;
    private String btchNum;
    private String localPlant;
    private String localStorLocation;
    private String btchExpDt;
    private String btchMfgDt;
    private String plant;
    private String materialNumber;
    private String btchStsCd;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("matlNum", this.matlNum)
                .add("btchNum", this.btchNum)
                .add("localPlant", this.localPlant)
                .add("localStorLocation", this.localStorLocation)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getBtchNum() {
        return this.btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
    }

    public String getLocalPlant() {
        return this.localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalStorLocation() {
        return this.localStorLocation;
    }

    public void setLocalStorLocation(String localStorLocation) {
        this.localStorLocation = localStorLocation;
    }

    public String getBtchExpDt() {
        return this.btchExpDt;
    }

    public void setBtchExpDt(String btchExpDt) {
        this.btchExpDt = btchExpDt;
    }

    public String getBtchMfgDt() {
        return this.btchMfgDt;
    }

    public void setBtchMfgDt(String btchMfgDt) {
        this.btchMfgDt = btchMfgDt;
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

    public String getBtchStsCd() {
        return this.btchStsCd;
    }

    public void setBtchStsCd(String btchStsCd) {
        this.btchStsCd = btchStsCd;
    }

}
