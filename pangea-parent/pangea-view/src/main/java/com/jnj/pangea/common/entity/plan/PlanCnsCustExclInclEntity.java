package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsCustExclInclEntity extends CommonEntity {
    private String country;
    private String salesOrg;
    private String customerShipTo;
    private String inclExcl;
    private String sourceSystem;

    public PlanCnsCustExclInclEntity(Map<String, Object> map) {
        super(map);
        setCountry((String) map.get("country"));
        setSalesOrg((String) map.get("salesOrg"));
        setCustomerShipTo((String) map.get("customerShipTo"));
        setInclExcl((String) map.get("inclExcl"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getCustomerShipTo() {
        return customerShipTo;
    }

    public void setCustomerShipTo(String customerShipTo) {
        this.customerShipTo = customerShipTo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInclExcl() {
        return inclExcl;
    }

    public void setInclExcl(String inclExcl) {
        this.inclExcl = inclExcl;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
}
