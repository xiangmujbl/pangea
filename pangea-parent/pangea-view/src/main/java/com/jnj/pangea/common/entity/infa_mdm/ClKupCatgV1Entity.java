package com.jnj.pangea.common.entity.infa_mdm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ClKupCatgV1Entity extends CommonEntity {

    private String catgCd;
    private String catDescnTxt;
    public ClKupCatgV1Entity(Map<String, Object> map) {
        super(map);
        setCatgCd((String) map.get("catgCd"));
        setCatDescnTxt((String) map.get("catDescnTxt"));
    }
    public String getCatgCd() {
        return catgCd;
    }

    public void setCatgCd(String catgCd) {
        this.catgCd = catgCd;
    }

    public String getCatDescnTxt() {
        return catDescnTxt;
    }

    public void setCatDescnTxt(String catDescnTxt) {
        this.catDescnTxt = catDescnTxt;
    }
}
