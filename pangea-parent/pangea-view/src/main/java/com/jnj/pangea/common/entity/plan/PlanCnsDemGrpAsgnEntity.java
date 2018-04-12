package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsDemGrpAsgnEntity extends CommonEntity {

    private String demandGroup;
    private String customerId;
    private String salesOrganization;

    public PlanCnsDemGrpAsgnEntity(Map<String, Object> map) {
        super(map);

        setDemandGroup((String) map.get("demandGroup"));
        setCustomerId((String) map.get("customerId"));
        setSalesOrganization((String) map.get("salesOrganization"));
    }

    public String getDemandGroup() {
        return this.demandGroup;
    }

    public void setDemandGroup(String demandGroup) {
        this.demandGroup = demandGroup;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSalesOrganization() {
        return this.salesOrganization;
    }

    public void setSalesOrganization(String salesOrganization) {
        this.salesOrganization = salesOrganization;
    }

}
