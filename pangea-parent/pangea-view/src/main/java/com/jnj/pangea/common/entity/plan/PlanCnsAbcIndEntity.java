package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsAbcIndEntity extends CommonEntity {

    private String indicator;
    private String localIndicator;
    private String sourceSystem;

    public PlanCnsAbcIndEntity(Map<String, Object> map) {
        super(map);

        setIndicator((String) map.get("indicator"));
        setLocalIndicator((String) map.get("localIndicator"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getIndicator() {
        return this.indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getLocalIndicator() {
        return this.localIndicator;
    }

    public void setLocalIndicator(String localIndicator) {
        this.localIndicator = localIndicator;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

}
