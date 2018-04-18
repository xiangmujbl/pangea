package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class KnvhEntity extends CommonEntity {

    private String kunnr;
    private String hkunnr;
    private String datbi;

    public KnvhEntity(Map<String, Object> map) {
        super(map);

        setKunnr((String) map.get("kunnr"));
        setHkunnr((String) map.get("hkunnr"));
        setDatbi((String) map.get("datbi"));
    }

    public String getKunnr() {
        return this.kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getHkunnr() {
        return this.hkunnr;
    }

    public void setHkunnr(String hkunnr) {
        this.hkunnr = hkunnr;
    }

    public String getDatbi() {
        return this.datbi;
    }

    public void setDatbi(String datbi) {
        this.datbi = datbi;
    }

}
