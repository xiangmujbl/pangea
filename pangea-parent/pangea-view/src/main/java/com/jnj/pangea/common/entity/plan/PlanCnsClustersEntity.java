package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsClustersEntity extends CommonEntity {

    private String cluster;
    private String countryId;
    private String subCluster;

    public PlanCnsClustersEntity(Map<String, Object> map) {
        super(map);

        setCluster((String) map.get("cluster"));
        setCountryId((String) map.get("countryId"));
        setSubCluster((String) map.get("subCluster"));
    }

    public String getCluster() {
        return this.cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getSubCluster() {
        return this.subCluster;
    }

    public void setSubCluster(String subCluster) {
        this.subCluster = subCluster;
    }

}
