package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneRkpfEntity extends CommonEntity {

    private String rsnum;
    private String kzver;
    private String xcale;
    private String rsdat;

    public ProjectOneRkpfEntity(Map<String, Object> map) {
        super(map);

        setRsnum((String) map.get("rsnum"));
        setKzver((String) map.get("kzver"));
        setXcale((String) map.get("xcale"));
        setRsdat((String) map.get("rsdat"));
    }

    public String getRsnum() {
        return this.rsnum;
    }

    public void setRsnum(String rsnum) {
        this.rsnum = rsnum;
    }

    public String getKzver() {
        return this.kzver;
    }

    public void setKzver(String kzver) {
        this.kzver = kzver;
    }

    public String getXcale() {
        return this.xcale;
    }

    public void setXcale(String xcale) {
        this.xcale = xcale;
    }

    public String getRsdat() {
        return this.rsdat;
    }

    public void setRsdat(String rsdat) {
        this.rsdat = rsdat;
    }

}
