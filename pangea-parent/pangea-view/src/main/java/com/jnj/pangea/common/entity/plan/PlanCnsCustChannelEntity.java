package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsCustChannelEntity extends CommonEntity {

    private String channel;

    public PlanCnsCustChannelEntity(Map<String, Object> map) {
        super(map);

        setChannel((String) map.get("channel"));
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
