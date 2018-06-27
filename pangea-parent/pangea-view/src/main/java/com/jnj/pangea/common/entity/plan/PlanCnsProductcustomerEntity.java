package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProductcustomerEntity extends CommonEntity {

    private String sourceSystem;
    private String demandGroup;
    private String productId;

    public PlanCnsProductcustomerEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setDemandGroup((String) map.get("demandGroup"));
        setProductId((String) map.get("productId"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDemandGroup() {
        return this.demandGroup;
    }

    public void setDemandGroup(String demandGroup) {
        this.demandGroup = demandGroup;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
