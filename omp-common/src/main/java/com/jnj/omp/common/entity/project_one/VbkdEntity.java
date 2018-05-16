package com.jnj.omp.common.entity.project_one;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class VbkdEntity extends CommonEntity {

    private String vbeln;
    private String posnr;
    private String inco1;
    private String inco2;
    private String kdgrp;

    public VbkdEntity(Map<String, Object> map) {
        super(map);

        setVbeln((String) map.get("vbeln"));
        setPosnr((String) map.get("posnr"));
        setInco1((String) map.get("inco1"));
        setInco2((String) map.get("inco2"));
        setKdgrp((String) map.get("kdgrp"));
    }

    public String getVbeln() {
        return vbeln;
    }

    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }

    public String getPosnr() {
        return posnr;
    }

    public void setPosnr(String posnr) {
        this.posnr = posnr;
    }

    public String getInco1() {
        return inco1;
    }

    public void setInco1(String inco1) {
        this.inco1 = inco1;
    }

    public String getInco2() {
        return inco2;
    }

    public void setInco2(String inco2) {
        this.inco2 = inco2;
    }

    public String getKdgrp() {
        return kdgrp;
    }

    public void setKdgrp(String kdgrp) {
        this.kdgrp = kdgrp;
    }
}
