package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsDemGrpAsgnEntity extends CommonEntity {

    private String countryAffiliate;
    private String customerId;
    private String demandGroup;
    private String channel;
    private String channelDescription;
    private String customerName;

    public PlanCnsDemGrpAsgnEntity(Map<String, Object> map) {
        super(map);

        setCountryAffiliate((String) map.get("countryAffiliate"));
        setCustomerId((String) map.get("customerId"));
        setDemandGroup((String) map.get("demandGroup"));
        setChannel((String) map.get("channel"));
        setChannelDescription((String) map.get("channelDescription"));
        setCustomerName((String) map.get("customerName"));
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

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
