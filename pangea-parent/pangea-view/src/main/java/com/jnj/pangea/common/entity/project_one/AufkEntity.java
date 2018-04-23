package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class AufkEntity extends CommonEntity {

    private String aufnr;
    private String auart;
    private String autyp;
    private String erdat;
    private String aedat;
    private String werks;
    private String loekz;
    private String idat2;
    private String procnr;
    private String logsystem;

    public AufkEntity(Map<String, Object> map) {
        super(map);

        setAufnr((String) map.get("aufnr"));
        setAuart((String) map.get("auart"));
        setAutyp((String) map.get("autyp"));
        setErdat((String) map.get("erdat"));
        setAedat((String) map.get("aedat"));
        setWerks((String) map.get("werks"));
        setLoekz((String) map.get("loekz"));
        setIdat2((String) map.get("idat2"));
        setProcnr((String) map.get("procnr"));
        setLogsystem((String) map.get("logsystem"));
    }

    public String getAufnr() {
        return this.aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getAuart() {
        return this.auart;
    }

    public void setAuart(String auart) {
        this.auart = auart;
    }

    public String getAutyp() {
        return this.autyp;
    }

    public void setAutyp(String autyp) {
        this.autyp = autyp;
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

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getLoekz() {
        return this.loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public String getIdat2() {
        return this.idat2;
    }

    public void setIdat2(String idat2) {
        this.idat2 = idat2;
    }

    public String getProcnr() {
        return this.procnr;
    }

    public void setProcnr(String procnr) {
        this.procnr = procnr;
    }

    public String getLogsystem() {
        return this.logsystem;
    }

    public void setLogsystem(String logsystem) {
        this.logsystem = logsystem;
    }

}
