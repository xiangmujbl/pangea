package com.jnj.pangea.plan.cns_abc_ind.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsAbcIndBo extends BaseBo {
    private String sourceSystem = "";
    private String localIndicator = "";
    private String localIndicatorDescription = "";
    private String indicator = "";
    private String indicatorDescription = "";

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalIndicator() {
        return localIndicator;
    }

    public void setLocalIndicator(String localIndicator) {
        this.localIndicator = localIndicator;
    }

    public String getLocalIndicatorDescription() {
        return localIndicatorDescription;
    }

    public void setLocalIndicatorDescription(String localIndicatorDescription) {
        this.localIndicatorDescription = localIndicatorDescription;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getIndicatorDescription() {
        return indicatorDescription;
    }

    public void setIndicatorDescription(String indicatorDescription) {
        this.indicatorDescription = indicatorDescription;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localIndicator", this.localIndicator)
                .add("localIndicatorDescription", this.localIndicatorDescription)
                .add("indicator", this.indicator)
                .add("indicatorDescription", this.indicatorDescription)
                .toJsonString();
    }
}
