package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EkesEntity extends CommonEntity {

    private String ebeln;
    private String ebelp;
    private String etens;
    private String mandt;

    public EkesEntity(Map<String, Object> map) {
        super(map);
        setEbeln((String)map.get("ebeln"));
        setEbelp((String)map.get("ebelp"));
        setEtens((String)map.get("etens"));
        setMandt((String)map.get("mandt"));
    }

    public String getEbeln() {
        return ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getEbelp() {
        return ebelp;
    }

    public void setEbelp(String ebelp) {
        this.ebelp = ebelp;
    }

    public String getEtens() {
        return etens;
    }

    public void setEtens(String etens) {
        this.etens = etens;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }
}
