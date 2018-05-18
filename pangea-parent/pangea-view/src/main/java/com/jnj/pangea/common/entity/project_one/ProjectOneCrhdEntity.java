package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneCrhdEntity extends CommonEntity {

    private String objty;
    private String objid;
    private String begda;
    private String endda;
    private String arbpl;
    private String werks;
    private String verwe;
    private String lvorm;
    private String planv;
    private String stand;
    private String veran;
    private String vgwts;
    private String xsprr;
    private String xterm;
    private String rasch;
    private String steus;
    private String fort1;
    private String fort2;
    private String fort3;
    private String kapid;
    private String ortgr;
    private String matyp;
    private String cplgr;
    private String fortn;
    private String prvbe;
    private String lgort_res;
    private String mixmat;

    public ProjectOneCrhdEntity(Map<String, Object> map) {
        super(map);

        setObjty((String) map.get("objty"));
        setObjid((String) map.get("objid"));
        setBegda((String) map.get("begda"));
        setEndda((String) map.get("endda"));
        setArbpl((String) map.get("arbpl"));
        setWerks((String) map.get("werks"));
        setVerwe((String) map.get("verwe"));
        setLvorm((String) map.get("lvorm"));
        setPlanv((String) map.get("planv"));
        setStand((String) map.get("stand"));
        setVeran((String) map.get("veran"));
        setVgwts((String) map.get("vgwts"));
        setXsprr((String) map.get("xsprr"));
        setXterm((String) map.get("xterm"));
        setRasch((String) map.get("rasch"));
        setSteus((String) map.get("steus"));
        setFort1((String) map.get("fort1"));
        setFort2((String) map.get("fort2"));
        setFort3((String) map.get("fort3"));
        setKapid((String) map.get("kapid"));
        setOrtgr((String) map.get("ortgr"));
        setMatyp((String) map.get("matyp"));
        setCplgr((String) map.get("cplgr"));
        setFortn((String) map.get("fortn"));
        setPrvbe((String) map.get("prvbe"));
        setLgort_res((String) map.get("lgort_res"));
        setMixmat((String) map.get("mixmat"));
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

    public String getBegda() {
        return this.begda;
    }

    public void setBegda(String begda) {
        this.begda = begda;
    }

    public String getEndda() {
        return this.endda;
    }

    public void setEndda(String endda) {
        this.endda = endda;
    }

    public String getArbpl() {
        return this.arbpl;
    }

    public void setArbpl(String arbpl) {
        this.arbpl = arbpl;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getVerwe() {
        return this.verwe;
    }

    public void setVerwe(String verwe) {
        this.verwe = verwe;
    }

    public String getLvorm() {
        return this.lvorm;
    }

    public void setLvorm(String lvorm) {
        this.lvorm = lvorm;
    }

    public String getPlanv() {
        return this.planv;
    }

    public void setPlanv(String planv) {
        this.planv = planv;
    }

    public String getStand() {
        return this.stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getVeran() {
        return this.veran;
    }

    public void setVeran(String veran) {
        this.veran = veran;
    }

    public String getVgwts() {
        return this.vgwts;
    }

    public void setVgwts(String vgwts) {
        this.vgwts = vgwts;
    }

    public String getXsprr() {
        return this.xsprr;
    }

    public void setXsprr(String xsprr) {
        this.xsprr = xsprr;
    }

    public String getXterm() {
        return this.xterm;
    }

    public void setXterm(String xterm) {
        this.xterm = xterm;
    }

    public String getRasch() {
        return this.rasch;
    }

    public void setRasch(String rasch) {
        this.rasch = rasch;
    }

    public String getSteus() {
        return this.steus;
    }

    public void setSteus(String steus) {
        this.steus = steus;
    }

    public String getFort1() {
        return this.fort1;
    }

    public void setFort1(String fort1) {
        this.fort1 = fort1;
    }

    public String getFort2() {
        return this.fort2;
    }

    public void setFort2(String fort2) {
        this.fort2 = fort2;
    }

    public String getFort3() {
        return this.fort3;
    }

    public void setFort3(String fort3) {
        this.fort3 = fort3;
    }

    public String getKapid() {
        return this.kapid;
    }

    public void setKapid(String kapid) {
        this.kapid = kapid;
    }

    public String getOrtgr() {
        return this.ortgr;
    }

    public void setOrtgr(String ortgr) {
        this.ortgr = ortgr;
    }

    public String getMatyp() {
        return this.matyp;
    }

    public void setMatyp(String matyp) {
        this.matyp = matyp;
    }

    public String getCplgr() {
        return this.cplgr;
    }

    public void setCplgr(String cplgr) {
        this.cplgr = cplgr;
    }

    public String getFortn() {
        return this.fortn;
    }

    public void setFortn(String fortn) {
        this.fortn = fortn;
    }

    public String getPrvbe() {
        return this.prvbe;
    }

    public void setPrvbe(String prvbe) {
        this.prvbe = prvbe;
    }

    public String getLgort_res() {
        return this.lgort_res;
    }

    public void setLgort_res(String lgort_res) {
        this.lgort_res = lgort_res;
    }

    public String getMixmat() {
        return this.mixmat;
    }

    public void setMixmat(String mixmat) {
        this.mixmat = mixmat;
    }

}
