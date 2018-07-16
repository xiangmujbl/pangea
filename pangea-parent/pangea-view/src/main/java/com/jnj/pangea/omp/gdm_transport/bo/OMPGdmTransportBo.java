package com.jnj.pangea.omp.gdm_transport.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmTransportBo extends BaseBo {

    private String transportId;
    private String active;
    private String activeOPRERP;
    private String activeSOPERP;
    private String endEff;
    private String fromLocationId;
    private String fromProductId;
    private String label;
    private String machineTypeId;
    private String minQuantity;
    private String planLevelId;
    private String processTypeId;
    private String purchasingGroup;
    private String purchasingOrganization;
    private String startEff;
    private String toLocationId;
    private String toProductId;
    private String transportOffset;
    private String transportType;
    private String vendorId;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("transportId", this.transportId)
                .toJsonString();
    }


    public String getTransportId () {
        return transportId;
    }

    public void setTransportId (String transportId) {
        this.transportId = transportId;
    }

    public String getActive () {
        return active;
    }

    public void setActive (String active) {
        this.active = active;
    }

    public String getActiveOPRERP () {
        return activeOPRERP;
    }

    public void setActiveOPRERP (String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP () {
        return activeSOPERP;
    }

    public void setActiveSOPERP (String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getEndEff () {
        return endEff;
    }

    public void setEndEff (String endEff) {
        this.endEff = endEff;
    }

    public String getFromLocationId () {
        return fromLocationId;
    }

    public void setFromLocationId (String fromLocationId) {
        this.fromLocationId = fromLocationId;
    }

    public String getFromProductId () {
        return fromProductId;
    }

    public void setFromProductId (String fromProductId) {
        this.fromProductId = fromProductId;
    }

    public String getLabel () {
        return label;
    }

    public void setLabel (String label) {
        this.label = label;
    }

    public String getMachineTypeId () {
        return machineTypeId;
    }

    public void setMachineTypeId (String machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public String getMinQuantity () {
        return minQuantity;
    }

    public void setMinQuantity (String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getPlanLevelId () {
        return planLevelId;
    }

    public void setPlanLevelId (String planLevelId) {
        this.planLevelId = planLevelId;
    }

    public String getProcessTypeId () {
        return processTypeId;
    }

    public void setProcessTypeId (String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getPurchasingGroup () {
        return purchasingGroup;
    }

    public void setPurchasingGroup (String purchasingGroup) {
        this.purchasingGroup = purchasingGroup;
    }

    public String getPurchasingOrganization () {
        return purchasingOrganization;
    }

    public void setPurchasingOrganization (String purchasingOrganization) {
        this.purchasingOrganization = purchasingOrganization;
    }



    public String getStartEff () {
        return startEff;
    }

    public void setStartEff (String startEff) {
        this.startEff = startEff;
    }

    public String getToLocationId () {
        return toLocationId;
    }

    public void setToLocationId (String toLocationId) {
        this.toLocationId = toLocationId;
    }

    public String getToProductId () {
        return toProductId;
    }

    public void setToProductId (String toProductId) {
        this.toProductId = toProductId;
    }

    public String getTransportOffset () {
        return transportOffset;
    }

    public void setTransportOffset (String transportOffset) {
        this.transportOffset = transportOffset;
    }

    public String getTransportType () {
        return transportType;
    }

    public void setTransportType (String transportType) {
        this.transportType = transportType;
    }

    public String getVendorId () {
        return vendorId;
    }

    public void setVendorId (String vendorId) {
        this.vendorId = vendorId;
    }
}
