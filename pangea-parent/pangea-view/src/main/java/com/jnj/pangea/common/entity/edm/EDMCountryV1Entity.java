package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMCountryV1Entity extends CommonEntity {

    private String countryName;
    private String countryCode;

    public EDMCountryV1Entity(Map<String, Object> map) {
        super(map);

        setCountryName((String) map.get("countryName"));
        setCountryCode((String) map.get("countryCode"));
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
