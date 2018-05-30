package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMBomItemEntity extends CommonEntity {

    private String bomNum;
    private String srcSysCd;
    private String bomCatCd;
    private String cmpntNum;
    private String bomItmNum;
    private String bomItmVldFromDt;
    private String dstrbtnKeyCd;
    private String fxQtyInd;
    private String bomItmVldToDt;
    private String leadTimeOffst;
    private String cmpntQty;
    private String cmpntScrap_Pct;
    private String cmpntUomCd;

    public EDMBomItemEntity(Map<String, Object> map) {
        super(map);
        setBomNum((String) map.get("bomNum"));
        setSrcSysCd((String) map.get("srcSysCd"));
        setBomCatCd((String) map.get("bomCatCd"));
        setCmpntNum((String) map.get("cmpntNum"));
        setBomItmNum((String) map.get("bomItmNum"));
        setDstrbtnKeyCd((String) map.get("dstrbtnKeyCd"));
        setFxQtyInd((String) map.get("fxQtyInd"));
        setBomItmVldToDt((String) map.get("bomItmVldToDt"));
        setLeadTimeOffst((String) map.get("leadTimeOffst"));
        setCmpntQty((String) map.get("cmpntQty"));
        setCmpntScrap_Pct((String) map.get("cmpntScrap_Pct"));
        setCmpntUomCd((String) map.get("cmpntUomCd"));
        setBomItmVldFromDt((String) map.get("bomItmVldFromDt"));
    }

    public String getBomNum() {
        return this.bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getBomCatCd() {
        return this.bomCatCd;
    }

    public void setBomCatCd(String bomCatCd) {
        this.bomCatCd = bomCatCd;
    }

    public String getCmpntNum() {
        return this.cmpntNum;
    }

    public void setCmpntNum(String cmpntNum) {
        this.cmpntNum = cmpntNum;
    }

    public String getBomItmNum() {
        return this.bomItmNum;
    }

    public void setBomItmNum(String bomItmNum) {
        this.bomItmNum = bomItmNum;
    }

    public String getDstrbtnKeyCd() {
        return this.dstrbtnKeyCd;
    }

    public void setDstrbtnKeyCd(String dstrbtnKeyCd) {
        this.dstrbtnKeyCd = dstrbtnKeyCd;
    }

    public String getFxQtyInd() {
        return this.fxQtyInd;
    }

    public void setFxQtyInd(String fxQtyInd) {
        this.fxQtyInd = fxQtyInd;
    }

    public String getBomItmVldToDt() {
        return this.bomItmVldToDt;
    }

    public void setBomItmVldToDt(String bomItmVldToDt) {
        this.bomItmVldToDt = bomItmVldToDt;
    }

    public String getLeadTimeOffst() {
        return this.leadTimeOffst;
    }

    public void setLeadTimeOffst(String leadTimeOffst) {
        this.leadTimeOffst = leadTimeOffst;
    }

    public String getCmpntQty() {
        return this.cmpntQty;
    }

    public void setCmpntQty(String cmpntQty) {
        this.cmpntQty = cmpntQty;
    }

    public String getCmpntScrap_Pct() {
        return this.cmpntScrap_Pct;
    }

    public void setCmpntScrap_Pct(String cmpntScrap_Pct) {
        this.cmpntScrap_Pct = cmpntScrap_Pct;
    }

    public String getCmpntUomCd() {
        return this.cmpntUomCd;
    }

    public void setCmpntUomCd(String cmpntUomCd) {
        this.cmpntUomCd = cmpntUomCd;
    }

    public String getBomItmVldFromDt() {
        return this.bomItmVldFromDt;
    }

    public void setBomItmVldFromDt(String bomItmVldFromDt) {
        this.bomItmVldFromDt = bomItmVldFromDt;
    }

    @Override
    public String toString() {
        return "EDMBomItemEntity{" +
                "bomNum='" + bomNum + '\'' +
                ", srcSysCd='" + srcSysCd + '\'' +
                ", bomCatCd='" + bomCatCd + '\'' +
                ", cmpntNum='" + cmpntNum + '\'' +
                ", bomItmNum='" + bomItmNum + '\'' +
                ", bomItmVldFromDt='" + bomItmVldFromDt + '\'' +
                ", dstrbtnKeyCd='" + dstrbtnKeyCd + '\'' +
                ", fxQtyInd='" + fxQtyInd + '\'' +
                ", bomItmVldToDt='" + bomItmVldToDt + '\'' +
                ", leadTimeOffst='" + leadTimeOffst + '\'' +
                ", cmpntQty='" + cmpntQty + '\'' +
                ", cmpntScrap_Pct='" + cmpntScrap_Pct + '\'' +
                ", cmpntUomCd='" + cmpntUomCd + '\'' +
                '}';
    }
}
