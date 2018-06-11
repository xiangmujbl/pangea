package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneCrcaEntity extends CommonEntity {

    private String objty;
    private String objid;
    private String canum;
    private String begda;
    private String endda;
    private String aedatKapa;
    private String kapid;
    private String fork1;
    private String fork2;
    private String fork3;
    private String forkn;
    private String vert1;
    private String vert2;
    private String vert3;
    private String vertn;

    public ProjectOneCrcaEntity(Map<String, Object> map) {
        super(map);

        setObjty((String) map.get("objty"));
        setObjid((String) map.get("objid"));
        setCanum((String) map.get("canum"));
        setBegda((String) map.get("begda"));
        setEndda((String) map.get("endda"));
        setAedatKapa((String) map.get("aedatKapa"));
        setKapid((String) map.get("kapid"));
        setFork1((String) map.get("fork1"));
        setFork2((String) map.get("fork2"));
        setFork3((String) map.get("fork3"));
        setForkn((String) map.get("forkn"));
        setVert1((String) map.get("vert1"));
        setVert2((String) map.get("vert2"));
        setVert3((String) map.get("vert3"));
        setVertn((String) map.get("vertn"));
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

    public String getCanum() {
        return this.canum;
    }

    public void setCanum(String canum) {
        this.canum = canum;
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

    public String getAedatKapa() {
        return this.aedatKapa;
    }

    public void setAedatKapa(String aedatKapa) {
        this.aedatKapa = aedatKapa;
    }

    public String getKapid() {
        return this.kapid;
    }

    public void setKapid(String kapid) {
        this.kapid = kapid;
    }

    public String getFork1() {
        return this.fork1;
    }

    public void setFork1(String fork1) {
        this.fork1 = fork1;
    }

    public String getFork2() {
        return this.fork2;
    }

    public void setFork2(String fork2) {
        this.fork2 = fork2;
    }

    public String getFork3() {
        return this.fork3;
    }

    public void setFork3(String fork3) {
        this.fork3 = fork3;
    }

    public String getForkn() {
        return this.forkn;
    }

    public void setForkn(String forkn) {
        this.forkn = forkn;
    }

    public String getVert1() {
        return this.vert1;
    }

    public void setVert1(String vert1) {
        this.vert1 = vert1;
    }

    public String getVert2() {
        return this.vert2;
    }

    public void setVert2(String vert2) {
        this.vert2 = vert2;
    }

    public String getVert3() {
        return this.vert3;
    }

    public void setVert3(String vert3) {
        this.vert3 = vert3;
    }

    public String getVertn() {
        return this.vertn;
    }

    public void setVertn(String vertn) {
        this.vertn = vertn;
    }

}
