package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MchbEntity extends CommonEntity {

    private String charg;
    private String lgort;
    private String ceinm;
    private String cinsm;
    private String clabs;
    private String cretm;
    private String cspem;
    private String cumlm;
    private String matnr;
    private String werks;

    public MchbEntity(Map<String, Object> map) {
        super(map);

        setCharg((String) map.get("charg"));
        setLgort((String) map.get("lgort"));
        setCeinm((String) map.get("ceinm"));
        setCinsm((String) map.get("cinsm"));
        setClabs((String) map.get("clabs"));
        setCretm((String) map.get("cretm"));
        setCspem((String) map.get("cspem"));
        setCumlm((String) map.get("cumlm"));
        setMatnr((String) map.get("matnr"));
        setWerks((String) map.get("werks"));
    }

    public String getCharg() {
        return this.charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getLgort() {
        return this.lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public String getCeinm() {
        return ceinm;
    }

    public void setCeinm(String ceinm) {
        this.ceinm = ceinm;
    }

    public String getCinsm() {
        return cinsm;
    }

    public void setCinsm(String cinsm) {
        this.cinsm = cinsm;
    }

    public String getClabs() {
        return clabs;
    }

    public void setClabs(String clabs) {
        this.clabs = clabs;
    }

    public String getCretm() {
        return cretm;
    }

    public void setCretm(String cretm) {
        this.cretm = cretm;
    }

    public String getCspem() {
        return cspem;
    }

    public void setCspem(String cspem) {
        this.cspem = cspem;
    }

    public String getCumlm() {
        return cumlm;
    }

    public void setCumlm(String cumlm) {
        this.cumlm = cumlm;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }
}
