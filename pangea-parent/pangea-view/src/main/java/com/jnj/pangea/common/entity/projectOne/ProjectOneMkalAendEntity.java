package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneMkalAendEntity extends CommonEntity {

    private String matnr;
    private String werks;
    private String verid;
    private String andat;
    private String aedat;

    public ProjectOneMkalAendEntity(Map<String, Object> map) {
        super(map);

        setMatnr((String) map.get("matnr"));
        setWerks((String) map.get("werks"));
        setVerid((String) map.get("verid"));
        setAndat((String) map.get("andat"));
        setAedat((String) map.get("aedat"));
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getVerid() {
        return this.verid;
    }

    public void setVerid(String verid) {
        this.verid = verid;
    }

    public String getAndat() {
        return this.andat;
    }

    public void setAndat(String andat) {
        this.andat = andat;
    }

    public String getAedat() {
        return this.aedat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }

}
