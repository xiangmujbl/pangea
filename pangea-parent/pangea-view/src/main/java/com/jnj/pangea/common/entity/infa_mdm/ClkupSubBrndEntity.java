package com.jnj.pangea.common.entity.infa_mdm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ClkupSubBrndEntity extends CommonEntity {

    private String subBrndCd;
    private String subBrndDescnTxt;
    public ClkupSubBrndEntity(Map<String, Object> map) {
        super(map);
        setSubBrndCd((String) map.get("subBrndCd"));
        setSubBrndDescnTxt((String) map.get("subBrndDescnTxt"));
    }

    public String getSubBrndCd() {
        return subBrndCd;
    }

    public void setSubBrndCd(String subBrndCd) {
        this.subBrndCd = subBrndCd;
    }

    public String getSubBrndDescnTxt() {
        return subBrndDescnTxt;
    }

    public void setSubBrndDescnTxt(String subBrndDescnTxt) {
        this.subBrndDescnTxt = subBrndDescnTxt;
    }
}
