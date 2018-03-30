package com.jnj.pangea.edm.country.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMCountryBo extends BaseBo {

    private String sourceSystem;
    private String localCountry;
    private String countryCode;
    private String countryName;
    private String consumerPlanningRegion;
    private String consumerPlannRegDesc;

    public String getConsumerPlannRegDesc() {
        return consumerPlannRegDesc;
    }

    public void setConsumerPlannRegDesc(String consumerPlannRegDesc) {
        this.consumerPlannRegDesc = consumerPlannRegDesc;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public void setLocalCountry(String localCountry) {
        this.localCountry = localCountry;
    }

    public String getLocalCountry() {

        return localCountry;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setConsumerPlanningRegion(String consumerPlanningRegion) {
        this.consumerPlanningRegion = consumerPlanningRegion;
    }

    public String getConsumerPlanningRegion() {

        return consumerPlanningRegion;
    }

    public void setCountryName(String countryName) {

        this.countryName = countryName;
    }

    public String getCountryName() {

        return countryName;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localCountry", this.localCountry)
                .toJsonString();
    }
}
