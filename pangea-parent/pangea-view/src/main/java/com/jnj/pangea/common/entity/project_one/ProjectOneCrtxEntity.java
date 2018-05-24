package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneCrtxEntity extends CommonEntity {

    private String objty;
    private String objid;
    private String spras;
    private String ktext;

    public ProjectOneCrtxEntity(Map<String, Object> map) {
        super(map);
        setObjty((String) map.get("objty"));
        setObjid((String) map.get("objid"));
        setSpras((String) map.get("spras"));
        setKtext((String) map.get("ktext"));
    }

    public String getObjty() {
        return objty;
    }

    public void setObjty(String objty) {
        this.objty = objty;
    }

    public String getObjid() {
        return this.objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public String getSpras() {
        return this.spras;
    }

    public void setSpras(String spras) {
        this.spras = spras;
    }

    public String getKtext() {
        return this.ktext;
    }

    public void setKtext(String ktext) {
        this.ktext = ktext;
    }

}
