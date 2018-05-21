package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EkpvEntity extends CommonEntity {
    private String ebelp;
    private String kunnr;
    private String ebeln;
    private String mandt;

    public EkpvEntity(Map<String, Object> map) {
        super(map);
        setEbelp((String)map.get("ebelp"));
        setKunnr((String)map.get("kunnr"));
        setEbeln((String)map.get("ebeln"));
        setMandt((String)map.get("mandt"));
    }

    public String getEbelp() {
        return ebelp;
    }

    public void setEbelp(String ebelp) {
        this.ebelp = ebelp;
    }

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getEbeln() {
        return ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }
}
