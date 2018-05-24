package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMBomHdrEntity extends CommonEntity {

    private String srcSysCd;
    private String altBomNum;
    private String bomNum;
    private String bomCatCd;
    private String bomVldFromDt;
    private String bomBaseQty;
    private String bomUomCd;
    private String bomVld_ToDt;

    public EDMBomHdrEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setAltBomNum((String) map.get("altBomNum"));
        setBomNum((String) map.get("bomNum"));
        setBomCatCd((String) map.get("bomCatCd"));
        setBomVldFromDt((String) map.get("bomVldFromDt"));
        setBomBaseQty((String) map.get("bomBaseQty"));
        setBomUomCd((String) map.get("bomUomCd"));
        setBomVld_ToDt((String) map.get("bomVld_ToDt"));
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getAltBomNum() {
        return this.altBomNum;
    }

    public void setAltBomNum(String altBomNum) {
        this.altBomNum = altBomNum;
    }

    public String getBomNum() {
        return this.bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getBomCatCd() {
        return this.bomCatCd;
    }

    public void setBomCatCd(String bomCatCd) {
        this.bomCatCd = bomCatCd;
    }

    public String getBomBaseQty() {
        return this.bomBaseQty;
    }

    public void setBomBaseQty(String bomBaseQty) {
        this.bomBaseQty = bomBaseQty;
    }

    public String getBomUomCd() {
        return this.bomUomCd;
    }

    public void setBomUomCd(String bomUomCd) {
        this.bomUomCd = bomUomCd;
    }

    public String getBomVldFromDt() {
        return this.bomVldFromDt;
    }

    public void setBomVldFromDt(String bomVldFromDt) {
        this.bomVldFromDt = bomVldFromDt;
    }

    public String getBomVld_ToDt() {
        return bomVld_ToDt;
    }

    public void setBomVld_ToDt(String bomVld_ToDt) {
        this.bomVld_ToDt = bomVld_ToDt;
    }
}
