package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsDemGrpAsgnEntity extends CommonEntity {

    private String sourceSystem;
    private String countryAffiliate;
    private String customerId;
    private String demandGroup;
    private String channel;
    private String channelDescription;
    private String customerName;
    private String group;
    private String salesOrg;

    public PlanCnsDemGrpAsgnEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setCountryAffiliate((String) map.get("countryAffiliate"));
        setCustomerId((String) map.get("customerId"));
        setDemandGroup((String) map.get("demandGroup"));
        setChannel((String) map.get("channel"));
        setChannelDescription((String) map.get("channelDescription"));
        setCustomerName((String) map.get("customerName"));
        setGroup((String) map.get("group"));
        setSalesOrg((String) map.get("salesOrg"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getCountryAffiliate() {
        return this.countryAffiliate;
    }

    public void setCountryAffiliate(String countryAffiliate) {
        this.countryAffiliate = countryAffiliate;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDemandGroup() {
        return this.demandGroup;
    }

    public void setDemandGroup(String demandGroup) {
        this.demandGroup = demandGroup;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelDescription() {
        return this.channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }
}
