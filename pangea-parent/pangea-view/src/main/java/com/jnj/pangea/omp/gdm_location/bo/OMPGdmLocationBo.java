package com.jnj.pangea.omp.gdm_location.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmLocationBo extends BaseBo {

    private String locationId;
    private String  active;
    private String  activeFCTERP;
    private String  activeOPRERP;
    private String  activeSOPERP;
    private String  countryId;
    private String  currencyId;
    private String  customerId;
    private String  label;
    private String  locationTypeId;
    private String  regionId;
    private String  vendorId;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("locationId",locationId)
                .toJsonString();
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveFCTERP() {
        return activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return activeSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLocationTypeId() {
        return locationTypeId;
    }

    public void setLocationTypeId(String locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

}
