package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MchaEntity extends CommonEntity {

    private String werks;
    private String matnr;
    private String charg;
    private String mandt;

    public MchaEntity(Map<String, Object> map) {
        super(map);

        setWerks((String) map.get("werks"));
        setMatnr((String) map.get("matnr"));
        setCharg((String) map.get("charg"));
        setMandt((String) map.get("mandt"));
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getCharg() {
        return this.charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getMandt() {
        return this.mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

}
