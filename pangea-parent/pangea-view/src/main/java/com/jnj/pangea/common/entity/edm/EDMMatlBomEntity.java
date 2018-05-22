package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * @author: qzhan112
 * @date: 2018/5/14
 */
public class EDMMatlBomEntity extends CommonEntity {
    private String srcSysCd;
    private String matlNum;
    private String plntCd;
    private String altBomNum;
    private String sourceSystem;
    private String bomNum;
    private String bomUsgCd;
    private String attribute;
    private String mandt;
    private String matnr;
    private String stlal;
    private String stlan;
    private String stlnr;
    private String werks;
    private String losvn;
    private String losbs;
    private String andat;
    private String aedat;

    public EDMMatlBomEntity(Map<String, Object> map) {
        super(map);

        setMandt((String) map.get("mandt"));
        setMatnr((String) map.get("matnr"));
        setStlal((String) map.get("stlal"));
        setStlan((String) map.get("stlan"));
        setStlnr((String) map.get("stlnr"));
        setWerks((String) map.get("werks"));
        setLosvn((String) map.get("losvn"));
        setLosbs((String) map.get("losbs"));
        setAedat((String) map.get("aedat"));
        setMatlNum((String) map.get("matlNum"));
        setPlntCd((String) map.get("plntCd"));
        setAltBomNum((String) map.get("altBomNum"));
        setSourceSystem((String) map.get("sourceSystem"));
        setBomNum((String) map.get("bomNum"));
        setBomUsgCd((String) map.get("bomUsgCd"));
        setAttribute((String) map.get("attribute"));
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getStlal() {
        return stlal;
    }

    public void setStlal(String stlal) {
        this.stlal = stlal;
    }

    public String getStlan() {
        return stlan;
    }

    public void setStlan(String stlan) {
        this.stlan = stlan;
    }

    public String getStlnr() {
        return stlnr;
    }

    public void setStlnr(String stlnr) {
        this.stlnr = stlnr;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getLosvn() {
        return losvn;
    }

    public void setLosvn(String losvn) {
        this.losvn = losvn;
    }

    public String getLosbs() {
        return losbs;
    }

    public void setLosbs(String losbs) {
        this.losbs = losbs;
    }

    public String getAndat() {
        return andat;
    }

    public void setAndat(String andat) {
        this.andat = andat;
    }

    public String getAedat() {
        return aedat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getMatlNum() {
        return matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getAltBomNum() {
        return altBomNum;
    }

    public void setAltBomNum(String altBomNum) {
        this.altBomNum = altBomNum;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getBomNum() {
        return bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getBomUsgCd() {
        return bomUsgCd;
    }

    public void setBomUsgCd(String bomUsgCd) {
        this.bomUsgCd = bomUsgCd;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
