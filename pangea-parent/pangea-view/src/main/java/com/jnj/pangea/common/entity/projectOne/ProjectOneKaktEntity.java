package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneKaktEntity extends CommonEntity {

    private String kapid;
    private String spras;
    private String ktext;

    public ProjectOneKaktEntity(Map<String, Object> map) {
        super(map);
        setKapid((String) map.get("kapid"));
        setSpras((String) map.get("spras"));
        setKtext((String) map.get("ktext"));
    }

    public String getKapid() {
        return kapid;
    }

    public void setKapid(String kapid) {
        this.kapid = kapid;
    }

    public String getSpras() {
        return spras;
    }

    public void setSpras(String spras) {
        this.spras = spras;
    }

    public String getKtext() {
        return ktext;
    }

    public void setKtext(String ktext) {
        this.ktext = ktext;
    }

    @Override
    public String toString() {
        return "ProjectOneKaktEntity{" +
                "kapid='" + kapid + '\'' +
                ", spras='" + spras + '\'' +
                ", ktext='" + ktext + '\'' +
                '}';
    }
}
