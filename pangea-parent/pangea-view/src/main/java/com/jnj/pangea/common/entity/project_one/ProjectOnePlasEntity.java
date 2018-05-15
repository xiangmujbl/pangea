package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOnePlasEntity extends CommonEntity {

    private String plnty;
    private String plnnr;
    private String plnal;
    private String plnfl;
    private String plnkn;
    private String zaehl;
    private String datuv;
    private String loekz;
    private String arnnr;

    public ProjectOnePlasEntity(Map<String, Object> map) {
        super(map);

        setPlnty((String) map.get("plnty"));
        setPlnnr((String) map.get("plnnr"));
        setPlnal((String) map.get("plnal"));
        setPlnfl((String) map.get("plnfl"));
        setPlnkn((String) map.get("plnkn"));
        setZaehl((String) map.get("zaehl"));
        setDatuv((String) map.get("datuv"));
        setLoekz((String) map.get("loekz"));
        setArnnr((String) map.get("arnnr"));
    }

    public String getPlnty() {
        return this.plnty;
    }

    public void setPlnty(String plnty) {
        this.plnty = plnty;
    }

    public String getPlnnr() {
        return this.plnnr;
    }

    public void setPlnnr(String plnnr) {
        this.plnnr = plnnr;
    }

    public String getPlnal() {
        return this.plnal;
    }

    public void setPlnal(String plnal) {
        this.plnal = plnal;
    }

    public String getPlnfl() {
        return this.plnfl;
    }

    public void setPlnfl(String plnfl) {
        this.plnfl = plnfl;
    }

    public String getPlnkn() {
        return this.plnkn;
    }

    public void setPlnkn(String plnkn) {
        this.plnkn = plnkn;
    }

    public String getZaehl() {
        return this.zaehl;
    }

    public void setZaehl(String zaehl) {
        this.zaehl = zaehl;
    }

    public String getDatuv() {
        return this.datuv;
    }

    public void setDatuv(String datuv) {
        this.datuv = datuv;
    }

    public String getLoekz() {
        return this.loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public String getArnnr() {
        return this.arnnr;
    }

    public void setArnnr(String arnnr) {
        this.arnnr = arnnr;
    }

}
