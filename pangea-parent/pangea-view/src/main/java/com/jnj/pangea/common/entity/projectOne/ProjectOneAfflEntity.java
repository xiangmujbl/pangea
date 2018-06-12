package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneAfflEntity extends CommonEntity {

    private String aufpl;
    private String aplzl;
    private String plnty;
    private String plnnr;
    private String plnal;
    private String plnfl;
    private String zaehl;
    private String aennr;
    private String flgat;
    private String bknt1;
    private String bknt2;
    private String losvn;
    private String losbs;
    private String bschl1;
    private String bschl2;

    public ProjectOneAfflEntity(Map<String, Object> map) {
        super(map);

        setAufpl((String) map.get("aufpl"));
        setAplzl((String) map.get("aplzl"));
        setPlnty((String) map.get("plnty"));
        setPlnnr((String) map.get("plnnr"));
        setPlnal((String) map.get("plnal"));
        setPlnfl((String) map.get("plnfl"));
        setZaehl((String) map.get("zaehl"));
        setAennr((String) map.get("aennr"));
        setFlgat((String) map.get("flgat"));
        setBknt1((String) map.get("bknt1"));
        setBknt2((String) map.get("bknt2"));
        setLosvn((String) map.get("losvn"));
        setLosbs((String) map.get("losbs"));
        setBschl1((String) map.get("bschl1"));
        setBschl2((String) map.get("bschl2"));
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

    public String getZaehl() {
        return this.zaehl;
    }

    public void setZaehl(String zaehl) {
        this.zaehl = zaehl;
    }

    public String getAennr() {
        return this.aennr;
    }

    public void setAennr(String aennr) {
        this.aennr = aennr;
    }

    public String getFlgat() {
        return this.flgat;
    }

    public void setFlgat(String flgat) {
        this.flgat = flgat;
    }

    public String getBknt1() {
        return this.bknt1;
    }

    public void setBknt1(String bknt1) {
        this.bknt1 = bknt1;
    }

    public String getBknt2() {
        return this.bknt2;
    }

    public void setBknt2(String bknt2) {
        this.bknt2 = bknt2;
    }

    public String getLosvn() {
        return this.losvn;
    }

    public void setLosvn(String losvn) {
        this.losvn = losvn;
    }

    public String getLosbs() {
        return this.losbs;
    }

    public void setLosbs(String losbs) {
        this.losbs = losbs;
    }

    public String getBschl1() {
        return this.bschl1;
    }

    public void setBschl1(String bschl1) {
        this.bschl1 = bschl1;
    }

    public String getBschl2() {
        return this.bschl2;
    }

    public void setBschl2(String bschl2) {
        this.bschl2 = bschl2;
    }

}
