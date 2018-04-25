package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class Tj02tEntity extends CommonEntity {

    private String istat;
    private String spras;
    private String txt04;
    private String txt30;

    public Tj02tEntity(Map<String, Object> map) {
        super(map);

        setIstat((String) map.get("istat"));
        setSpras((String) map.get("spras"));
        setTxt04((String) map.get("txt04"));
        setTxt30((String) map.get("txt30"));
    }

    public String getIstat() {
        return this.istat;
    }

    public void setIstat(String istat) {
        this.istat = istat;
    }

    public String getSpras() {
        return this.spras;
    }

    public void setSpras(String spras) {
        this.spras = spras;
    }

    public String getTxt04() {
        return this.txt04;
    }

    public void setTxt04(String txt04) {
        this.txt04 = txt04;
    }

    public String getTxt30() {
        return this.txt30;
    }

    public void setTxt30(String txt30) {
        this.txt30 = txt30;
    }

}
