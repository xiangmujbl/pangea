package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public class MarmEntity extends CommonEntity {

    private String matnr;
    private String meinh;
    private String umrez;
    private String umren;

    public MarmEntity(Map<String, Object> map) {
        super(map);
        setMatnr((String)map.get("matnr"));
        setMeinh((String)map.get("meinh"));
        setUmrez((String)map.get("umrez"));
        setUmren((String)map.get("umren"));
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMeinh() {
        return meinh;
    }

    public void setMeinh(String meinh) {
        this.meinh = meinh;
    }

    public String getUmrez() {
        return umrez;
    }

    public void setUmrez(String umrez) {
        this.umrez = umrez;
    }

    public String getUmren() {
        return umren;
    }

    public void setUmren(String umren) {
        this.umren = umren;
    }
}
