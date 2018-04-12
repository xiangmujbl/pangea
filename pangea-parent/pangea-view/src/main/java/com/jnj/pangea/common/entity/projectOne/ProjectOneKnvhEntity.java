package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneKnvhEntity extends CommonEntity {

    private String kunnr;
    private String vkorg;
    private String datbi;
    private String hkunnr;

    public ProjectOneKnvhEntity(Map<String, Object> map) {
        super(map);

        setKunnr((String) map.get("kunnr"));
        setVkorg((String) map.get("vkorg"));
        setDatbi((String) map.get("datbi"));
        setHkunnr((String) map.get("hkunnr"));
    }

    public String getKunnr() {
        return this.kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getVkorg() {
        return this.vkorg;
    }

    public void setVkorg(String vkorg) {
        this.vkorg = vkorg;
    }

    public String getDatbi() {
        return this.datbi;
    }

    public void setDatbi(String datbi) {
        this.datbi = datbi;
    }

    public String getHkunnr() {
        return this.hkunnr;
    }

    public void setHkunnr(String hkunnr) {
        this.hkunnr = hkunnr;
    }

}
