package com.jnj.omp.common.entity.project_one;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class MaktEntity extends CommonEntity {

    private String matnr;
    private String spras;
    private String maktx;

    public MaktEntity(Map<String, Object> map) {
        super(map);
        setMatnr((String) map.get("matnr"));
        setSpras((String) map.get("spras"));
        setMaktx((String) map.get("maktx"));
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getSpras() {
        return spras;
    }

    public void setSpras(String spras) {
        this.spras = spras;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }
}
