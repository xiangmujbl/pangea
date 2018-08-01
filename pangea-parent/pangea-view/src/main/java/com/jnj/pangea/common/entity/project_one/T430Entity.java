package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T430Entity extends CommonEntity {

    private String mandt;
    private String steus;
    private String term;

    public T430Entity(Map<String, Object> map) {
        super(map);
        setMandt((String) map.get("mandt"));
        setSteus((String) map.get("steus"));
        setTerm((String) map.get("term"));
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getSteus() {
        return steus;
    }

    public void setSteus(String steus) {
        this.steus = steus;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
