package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsSoTypeInclEntity extends CommonEntity {

    private String salesOrg;
    private String orderType;
    private String country;

    public PlanCnsSoTypeInclEntity(Map<String, Object> map) {
        super(map);

        setSalesOrg((String) map.get("salesOrg"));
        setOrderType((String) map.get("orderType"));
        setCountry((String) map.get("country"));
    }

    public String getSalesOrg() {
        return this.salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
