package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MarcEntity extends CommonEntity {
    private String matnr;
    private String werks;
    private String mmsta;

    public MarcEntity(Map<String, Object> map) {
        super(map);
        setMatnr((String)map.get("matnr"));
        setWerks((String)map.get("werks"));
        setMmsta((String)map.get("mmsta"));
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

    public String getMmsta() {
        return mmsta;
    }

    public void setMmsta(String mmsta) {
        this.mmsta = mmsta;
    }
}
