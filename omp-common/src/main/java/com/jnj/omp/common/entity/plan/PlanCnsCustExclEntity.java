package com.jnj.omp.common.entity.plan;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsCustExclEntity extends CommonEntity{
    private String salesOrg;
    private String customerShipTo;

    public PlanCnsCustExclEntity(Map<String, Object> map) {
        super(map);
        setSalesOrg((String) map.get("salesOrg"));
        setCustomerShipTo((String) map.get("customerShipTo"));
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
}
