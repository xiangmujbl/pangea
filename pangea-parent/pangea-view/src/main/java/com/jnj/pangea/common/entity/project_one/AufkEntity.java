package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class AufkEntity extends CommonEntity {

    private String aufnr;
    private String mandt;
    private String auart;
    private String werks;
    private String erdat;
    private String aedat;
    private String loekz;
    private String objnr;
    private String autyp;
    private String idat2;
    private String procnr;

    public String getAutyp() {
        return autyp;
    }

    public void setAutyp(String autyp) {
        this.autyp = autyp;
    }

    public String getIdat2() {
        return idat2;
    }

    public void setIdat2(String idat2) {
        this.idat2 = idat2;
    }

    public String getProcnr() {
        return procnr;
    }

    public void setProcnr(String procnr) {
        this.procnr = procnr;
    }

    public String getLogsystem() {
        return logsystem;
    }

    public void setLogsystem(String logsystem) {
        this.logsystem = logsystem;
    }

    private String logsystem;




    public AufkEntity(Map<String, Object> map) {
        super(map);

        setAufnr((String) map.get("aufnr"));
        setMandt((String) map.get("mandt"));
        setAuart((String) map.get("auart"));
        setWerks((String) map.get("werks"));
        setErdat((String) map.get("erdat"));
        setAedat((String) map.get("aedat"));
        setLoekz((String) map.get("loekz"));
        setObjnr((String) map.get("objnr"));
        setAutyp((String) map.get("autyp"));
        setIdat2((String) map.get("idat2"));
        setProcnr((String) map.get("procnr"));
    }

    public String getAufnr() {
        return this.aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getMandt() {
        return this.mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getAuart() {
        return this.auart;
    }

    public void setAuart(String auart) {
        this.auart = auart;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getErdat() {
        return this.erdat;
    }

    public void setErdat(String erdat) {
        this.erdat = erdat;
    }

    public String getAedat() {
        return this.aedat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }

    public String getLoekz() {
        return this.loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public String getObjnr() {
        return this.objnr;
    }

    public void setObjnr(String objnr) {
        this.objnr = objnr;
    }

}
