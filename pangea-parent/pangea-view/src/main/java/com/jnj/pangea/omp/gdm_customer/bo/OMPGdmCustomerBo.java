package com.jnj.pangea.omp.gdm_customer.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmCustomerBo extends BaseBo {

    private String customerId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String aggrSoldTo;
    private String channel;
    private String channelDescription;
    private String countryId;
    private String custCluster;
    private String distributionChannel;
    private String distributor;
    private String division;
    private String eCommerce;
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
    private String subCluster;
    private String ucn;

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

    public String getAggrSoldTo() {
        return this.aggrSoldTo;
    }

    public void setAggrSoldTo(String aggrSoldTo) {
        this.aggrSoldTo = aggrSoldTo;
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

    public String getCustCluster() {
        return this.custCluster;
    }

    public void setCustCluster(String custCluster) {
        this.custCluster = custCluster;
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

    public String getDivision() {
        return this.division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getECommerce() {
        return this.eCommerce;
    }

    public void setECommerce(String eCommerce) {
        this.eCommerce = eCommerce;
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

    public String getSubCluster() {
        return this.subCluster;
    }

    public void setSubCluster(String subCluster) {
        this.subCluster = subCluster;
    }

    public String getUcn() {
        return this.ucn;
    }

    public void setUcn(String ucn) {
        this.ucn = ucn;
    }

}
