package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * @Name: ProjectOneVbfaEntity
 * @Description: VBFA entity for project_one system
 * @author KG(Kelvin Gu)   
 * @date 06-12-2018 03:03:58 
*/
public class ProjectOneVbfaEntity extends CommonEntity {

    private String vbelv;
    private String posnv;
    private String vbeln;
    private String posnn;
    private String vbtypN;
    private String rfmng;
    private String meins;
    private String rfmngFlt;
    private String vrkme;
    private String vbtypV;
    private String erdat;

    public ProjectOneVbfaEntity(Map<String, Object> map) {
        super(map);

        setVbelv((String) map.get("vbelv"));
        setPosnv((String) map.get("posnv"));
        setVbeln((String) map.get("vbeln"));
        setPosnn((String) map.get("posnn"));
        setVbtypN((String) map.get("vbtypN"));
        setRfmng((String) map.get("rfmng"));
        setMeins((String) map.get("meins"));
        setRfmngFlt((String) map.get("rfmngFlt"));
        setVrkme((String) map.get("vrkme"));
        setVbtypV((String) map.get("vbtypV"));
        setErdat((String) map.get("erdat"));
    }

    public String getVbelv() {
        return vbelv;
    }
    public void setVbelv(String vbelv) {
        this.vbelv = vbelv;
    }
    public String getPosnv() {
        return posnv;
    }
    public void setPosnv(String posnv) {
        this.posnv = posnv;
    }
    public String getVbeln() {
        return vbeln;
    }
    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }
    public String getPosnn() {
        return posnn;
    }
    public void setPosnn(String posnn) {
        this.posnn = posnn;
    }
    public String getVbtypN() {
        return vbtypN;
    }
    public void setVbtypN(String vbtypN) {
        this.vbtypN = vbtypN;
    }
    public String getRfmng() {
        return rfmng;
    }
    public void setRfmng(String rfmng) {
        this.rfmng = rfmng;
    }
    public String getMeins() {
        return meins;
    }
    public void setMeins(String meins) {
        this.meins = meins;
    }
    public String getRfmngFlt() {
        return rfmngFlt;
    }
    public void setRfmngFlt(String rfmngFlt) {
        this.rfmngFlt = rfmngFlt;
    }
    public String getVrkme() {
        return vrkme;
    }
    public void setVrkme(String vrkme) {
        this.vrkme = vrkme;
    }
    public String getVbtypV() {
        return vbtypV;
    }
    public void setVbtypV(String vbtypV) {
        this.vbtypV = vbtypV;
    }
    public String getErdat() {
        return erdat;
    }
    public void setErdat(String erdat) {
        this.erdat = erdat;
    }
}
