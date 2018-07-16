package com.jnj.pangea.omp.gdm_customer.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmCustomerBo extends BaseBo {

    private String customerId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String channel;
    private String channelDescription;
    private String countryId;
    private String CUST_Cluster;
    private String distributionChannel;
    private String distributor;
    private String eCommerce;
    private String forecastSource;
    private String globalCustomerId;
    private String name;
    private String partner;
    private String partnerCountry;
    private String partnerName;
    private String partnerRegion;
    private String partnerRole;
    private String planningCustomerGroupId;
    private String regionId;
    private String salesOrganization;
    private String soldTo;
    private String sourceLocationId;
    private String subClusterId;
    private String currencyId;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("customerId", this.customerId)
                .toJsonString();
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveFCTERP() {
        return this.activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return this.activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return this.activeSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
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

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCUST_Cluster() {
		return this.CUST_Cluster;
	}

	public void setCUST_Cluster(String CUST_Cluster) {
        this.CUST_Cluster = CUST_Cluster;
	}

    public String getDistributionChannel() {
        return this.distributionChannel;
    }

    public void setDistributionChannel(String distributionChannel) {
        this.distributionChannel = distributionChannel;
    }

    public String getDistributor() {
        return this.distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getECommerce() {
        return this.eCommerce;
    }

    public void setECommerce(String eCommerce) {
        this.eCommerce = eCommerce;
    }

    public String getForecastSource() {
        return this.forecastSource;
    }

    public void setForecastSource(String forecastSource) {
        this.forecastSource = forecastSource;
    }

    public String getGlobalCustomerId() {
        return this.globalCustomerId;
    }

    public void setGlobalCustomerId(String globalCustomerId) {
        this.globalCustomerId = globalCustomerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartner() {
        return this.partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPartnerCountry() {
        return this.partnerCountry;
    }

    public void setPartnerCountry(String partnerCountry) {
        this.partnerCountry = partnerCountry;
    }

    public String getPartnerName() {
        return this.partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerRegion() {
        return this.partnerRegion;
    }

    public void setPartnerRegion(String partnerRegion) {
        this.partnerRegion = partnerRegion;
    }

    public String getPartnerRole() {
        return this.partnerRole;
    }

    public void setPartnerRole(String partnerRole) {
        this.partnerRole = partnerRole;
    }

    public String getPlanningCustomerGroupId() {
        return this.planningCustomerGroupId;
    }

    public void setPlanningCustomerGroupId(String planningCustomerGroupId) {
        this.planningCustomerGroupId = planningCustomerGroupId;
    }

    public String getRegionId() {
        return this.regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSalesOrganization() {
        return this.salesOrganization;
    }

    public void setSalesOrganization(String salesOrganization) {
        this.salesOrganization = salesOrganization;
    }

    public String getSoldTo() {
        return this.soldTo;
    }

    public void setSoldTo(String soldTo) {
        this.soldTo = soldTo;
    }

    public String getSourceLocationId() {
        return this.sourceLocationId;
    }

    public void setSourceLocationId(String sourceLocationId) {
        this.sourceLocationId = sourceLocationId;
    }

    public String getSubClusterId() {
        return this.subClusterId;
    }

    public void setSubClusterId(String subClusterId) {
        this.subClusterId = subClusterId;
    }

    public String getCurrencyId() {
        return this.currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

}
