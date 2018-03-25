package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class TmabctEntity extends CommonEntity {
    private String tmabc="";
    private String spars="";
    private String maabc="";

    public TmabctEntity(Map<String, Object> map) {
        super(map);
        setTmabc(map.get("tmabc")==null?"":(String)map.get("tmabc"));
        setMaabc(map.get("maabc") == null ?"":(String)map.get("maabc"));
        setSpars(map.get("spars")==null?"":(String)map.get("spars"));
    }

    public String getTmabc() {
        return tmabc;
    }

    public void setTmabc(String tmabc) {
        this.tmabc = tmabc;
    }

    public String getSpars() {
        return spars;
    }

    public void setSpars(String spars) {
        this.spars = spars;
    }

    public String getMaabc() {
        return maabc;
    }

    public void setMaabc(String maabc) {
        this.maabc = maabc;
    }
}
