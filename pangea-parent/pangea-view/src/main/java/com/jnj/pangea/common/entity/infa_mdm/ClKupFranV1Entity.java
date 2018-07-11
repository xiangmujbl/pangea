package com.jnj.pangea.common.entity.infa_mdm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ClKupFranV1Entity extends CommonEntity {

    private String franCd;
    private String franNm;
    public ClKupFranV1Entity(Map<String, Object> map) {
        super(map);
        setFranCd((String) map.get("franCd"));
        setFranNm((String) map.get("franNm"));
    }

    public String getFranCd() {
        return franCd;
    }

    public void setFranCd(String franCd) {
        this.franCd = franCd;
    }

    public String getFranNm() {
        return franNm;
    }

    public void setFranNm(String franNm) {
        this.franNm = franNm;
    }
}
