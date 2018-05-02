package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsCustChannelEntity extends CommonEntity {

    private String channel;
    private String salesOrg;

    public PlanCnsCustChannelEntity(Map<String, Object> map) {
        super(map);

        setChannel((String) map.get("channel"));
        setSalesOrg((String) map.get("salesOrg"));
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }
}
