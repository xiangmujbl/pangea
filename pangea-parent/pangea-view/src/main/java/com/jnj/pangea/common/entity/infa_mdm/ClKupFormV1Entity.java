package com.jnj.pangea.common.entity.infa_mdm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ClKupFormV1Entity extends CommonEntity {

    private String formCd;
    private String formNm;
    public ClKupFormV1Entity(Map<String, Object> map) {
        super(map);
        setFormCd((String) map.get("formCd"));
        setFormNm((String) map.get("formNm"));
    }

    public String getFormCd() {
        return formCd;
    }

    public void setFormCd(String formCd) {
        this.formCd = formCd;
    }

    public String getFormNm() {
        return formNm;
    }

    public void setFormNm(String formNm) {
        this.formNm = formNm;
    }
}
