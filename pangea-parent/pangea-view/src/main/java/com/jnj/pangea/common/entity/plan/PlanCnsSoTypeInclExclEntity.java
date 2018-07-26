package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsSoTypeInclExclEntity extends CommonEntity {
    private String sourceSystem;
    private String salesOrg;
    private String orderType;
    private String country;
    private String plant;
    private String inclExcl;

    public PlanCnsSoTypeInclExclEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setSalesOrg((String) map.get("salesOrg"));
        setOrderType((String) map.get("orderType"));
        setCountry((String) map.get("country"));
        setPlant((String) map.get("plant"));
        setInclExcl((String) map.get("inclExcl"));
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

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getInclExcl() {
        return inclExcl;
    }

    public void setInclExcl(String inclExcl) {
        this.inclExcl = inclExcl;
    }
}
