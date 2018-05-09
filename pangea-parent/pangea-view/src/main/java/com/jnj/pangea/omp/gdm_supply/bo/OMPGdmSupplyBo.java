package com.jnj.pangea.omp.gdm_supply.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmSupplyBo extends BaseBo {

    private String SupplyId;
    private String Active;
    private String ActiveOPRERP;
    private String ActiveSOPERP;
    private String FromDate;
    private String LABEL;
    private String LocationId;
    private String MACHINECAPACITY;
    private String MACHINETYPEID;
    private String MaxQuantity;
    private String MaxQuantityType;
    private String MinQuantity;
    private String MinQuantityType;
    private String PLANLEVELID;
    private String Preference;
    private String PROCESSTYPEID;
    private String ProductId;
    private String PURCHASINGGROUP;
    private String PURCHASINGORGANIZATION;
    private String SupplierId;
    private String ToDate;
    private String TransportType;
    private String VENDOR;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("supplyId", this.SupplyId)
                .toJsonString();
    }

    public String getSupplyId() {
        return SupplyId;
    }

    public void setSupplyId(String supplyId) {
        SupplyId = supplyId;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getActiveOPRERP() {
        return ActiveOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        ActiveOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return ActiveSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        ActiveSOPERP = activeSOPERP;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getLABEL() {
        return LABEL;
    }

    public void setLABEL(String LABEL) {
        this.LABEL = LABEL;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String locationId) {
        LocationId = locationId;
    }

    public String getMACHINECAPACITY() {
        return MACHINECAPACITY;
    }

    public void setMACHINECAPACITY(String MACHINECAPACITY) {
        this.MACHINECAPACITY = MACHINECAPACITY;
    }

    public String getMACHINETYPEID() {
        return MACHINETYPEID;
    }

    public void setMACHINETYPEID(String MACHINETYPEID) {
        this.MACHINETYPEID = MACHINETYPEID;
    }

    public String getMaxQuantity() {
        return MaxQuantity;
    }

    public void setMaxQuantity(String maxQuantity) {
        MaxQuantity = maxQuantity;
    }

    public String getMaxQuantityType() {
        return MaxQuantityType;
    }

    public void setMaxQuantityType(String maxQuantityType) {
        MaxQuantityType = maxQuantityType;
    }

    public String getMinQuantity() {
        return MinQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        MinQuantity = minQuantity;
    }

    public String getMinQuantityType() {
        return MinQuantityType;
    }

    public void setMinQuantityType(String minQuantityType) {
        MinQuantityType = minQuantityType;
    }

    public String getPLANLEVELID() {
        return PLANLEVELID;
    }

    public void setPLANLEVELID(String PLANLEVELID) {
        this.PLANLEVELID = PLANLEVELID;
    }

    public String getPreference() {
        return Preference;
    }

    public void setPreference(String preference) {
        Preference = preference;
    }

    public String getPROCESSTYPEID() {
        return PROCESSTYPEID;
    }

    public void setPROCESSTYPEID(String PROCESSTYPEID) {
        this.PROCESSTYPEID = PROCESSTYPEID;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getPURCHASINGGROUP() {
        return PURCHASINGGROUP;
    }

    public void setPURCHASINGGROUP(String PURCHASINGGROUP) {
        this.PURCHASINGGROUP = PURCHASINGGROUP;
    }

    public String getPURCHASINGORGANIZATION() {
        return PURCHASINGORGANIZATION;
    }

    public void setPURCHASINGORGANIZATION(String PURCHASINGORGANIZATION) {
        this.PURCHASINGORGANIZATION = PURCHASINGORGANIZATION;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public String getTransportType() {
        return TransportType;
    }

    public void setTransportType(String transportType) {
        TransportType = transportType;
    }

    public String getVENDOR() {
        return VENDOR;
    }

    public void setVENDOR(String VENDOR) {
        this.VENDOR = VENDOR;
    }

}
