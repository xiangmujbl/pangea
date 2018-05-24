package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsCustChannelEntity extends CommonEntity {

    private String channel;
    private String salesOrg;
    private String sourceSystem;
    private String channelDesc;

    public PlanCnsCustChannelEntity(Map<String, Object> map) {
        super(map);

        setChannel((String) map.get("channel"));
        setSalesOrg((String) map.get("salesOrg"));
        setSourceSystem((String) map.get("sourceSystem"));
        setChannelDesc((String) map.get("channelDesc"));
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

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }
}
