package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsSubClusterEntity extends CommonEntity {

    private String clusterId;
    private String subClusterId;
    private String description;

    public PlanCnsSubClusterEntity(Map<String, Object> map) {
        super(map);
        setClusterId((String) map.get("clusterId"));
        setSubClusterId((String) map.get("subClusterId"));
        setDescription((String) map.get("description"));
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getSubClusterId() {
        return subClusterId;
    }

    public void setSubClusterId(String subClusterId) {
        this.subClusterId = subClusterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
