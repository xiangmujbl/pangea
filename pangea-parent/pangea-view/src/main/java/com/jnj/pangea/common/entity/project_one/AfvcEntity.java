package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class AfvcEntity extends CommonEntity {

    private String aufpl;
    private String aplzl;
    private String vornr;
    private String steus;
    private String arbid;
    private String ltxa1;

    public AfvcEntity(Map<String, Object> map) {
        super(map);

        setAufpl((String) map.get("aufpl"));
        setAplzl((String) map.get("aplzl"));
        setVornr((String) map.get("vornr"));
        setSteus((String) map.get("steus"));
        setArbid((String) map.get("arbid"));
        setLtxa1((String) map.get("ltxa1"));
    }

    public String getAufpl() {
        return this.aufpl;
    }

    public void setAufpl(String aufpl) {
        this.aufpl = aufpl;
    }

    public String getAplzl() {
        return this.aplzl;
    }

    public void setAplzl(String aplzl) {
        this.aplzl = aplzl;
    }

    public String getVornr() {
        return this.vornr;
    }

    public void setVornr(String vornr) {
        this.vornr = vornr;
    }

    public String getSteus() {
        return this.steus;
    }

    public void setSteus(String steus) {
        this.steus = steus;
    }

    public String getArbid() {
        return this.arbid;
    }

    public void setArbid(String arbid) {
        this.arbid = arbid;
    }

    public String getLtxa1() {
        return this.ltxa1;
    }

    public void setLtxa1(String ltxa1) {
        this.ltxa1 = ltxa1;
    }

}
