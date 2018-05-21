package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneCrcoEntity extends CommonEntity {

    private String objty;
    private String objid;
    private String laset;
    private String endda;
    private String lanum;
    private String begda;
    private String aedatKost;
    private String kokrs;
    private String kostl;
    private String lstar;

    public ProjectOneCrcoEntity(Map<String, Object> map) {
        super(map);

        setObjty((String) map.get("objty"));
        setObjid((String) map.get("objid"));
        setLaset((String) map.get("laset"));
        setEndda((String) map.get("endda"));
        setLanum((String) map.get("lanum"));
        setBegda((String) map.get("begda"));
        setAedatKost((String) map.get("aedatKost"));
        setKokrs((String) map.get("kokrs"));
        setKostl((String) map.get("kostl"));
        setLstar((String) map.get("lstar"));
    }

    public String getObjty() {
        return this.objty;
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

    public String getLaset() {
        return this.laset;
    }

    public void setLaset(String laset) {
        this.laset = laset;
    }

    public String getEndda() {
        return this.endda;
    }

    public void setEndda(String endda) {
        this.endda = endda;
    }

    public String getLanum() {
        return this.lanum;
    }

    public void setLanum(String lanum) {
        this.lanum = lanum;
    }

    public String getBegda() {
        return this.begda;
    }

    public void setBegda(String begda) {
        this.begda = begda;
    }

    public String getAedatKost() {
        return this.aedatKost;
    }

    public void setAedatKost(String aedatKost) {
        this.aedatKost = aedatKost;
    }

    public String getKokrs() {
        return this.kokrs;
    }

    public void setKokrs(String kokrs) {
        this.kokrs = kokrs;
    }

    public String getKostl() {
        return this.kostl;
    }

    public void setKostl(String kostl) {
        this.kostl = kostl;
    }

    public String getLstar() {
        return this.lstar;
    }

    public void setLstar(String lstar) {
        this.lstar = lstar;
    }

}
