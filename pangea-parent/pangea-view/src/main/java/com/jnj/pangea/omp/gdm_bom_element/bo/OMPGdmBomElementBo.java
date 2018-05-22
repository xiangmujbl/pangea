package com.jnj.pangea.omp.gdm_bom_element.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmBomElementBo extends BaseBo {

    private String bomElementId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String batchId;
    private String bomId;
    private String bomType;
    private String bomUsage;
    private String comment;
    private String endEff;
    private String erpFeedbackQuantity;
    private String locationId;
    private String offset;
    private String offsetCalendarId;
    private String offsetPercentage;
    private String offsetType;
    private String planLevelId;
    private String productId;
    private String quantity;
    private String startEff;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("", "")
                .toJsonString();
    }

    public String getBomElementId() {
        return this.bomElementId;
    }

    public void setBomElementId(String bomElementId) {
        this.bomElementId = bomElementId;
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

    public String getBatchId() {
        return this.batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBomId() {
        return this.bomId;
    }

    public void setBomId(String bomId) {
        this.bomId = bomId;
    }

    public String getBomType() {
        return this.bomType;
    }

    public void setBomType(String bomType) {
        this.bomType = bomType;
    }

    public String getBomUsage() {
        return this.bomUsage;
    }

    public void setBomUsage(String bomUsage) {
        this.bomUsage = bomUsage;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEndEff() {
        return this.endEff;
    }

    public void setEndEff(String endEff) {
        this.endEff = endEff;
    }

    public String getErpFeedbackQuantity() {
        return this.erpFeedbackQuantity;
    }

    public void setErpFeedbackQuantity(String erpFeedbackQuantity) {
        this.erpFeedbackQuantity = erpFeedbackQuantity;
    }

    public String getLocationId() {
        return this.locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getOffset() {
        return this.offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getOffsetCalendarId() {
        return this.offsetCalendarId;
    }

    public void setOffsetCalendarId(String offsetCalendarId) {
        this.offsetCalendarId = offsetCalendarId;
    }

    public String getOffsetPercentage() {
        return this.offsetPercentage;
    }

    public void setOffsetPercentage(String offsetPercentage) {
        this.offsetPercentage = offsetPercentage;
    }

    public String getOffsetType() {
        return this.offsetType;
    }

    public void setOffsetType(String offsetType) {
        this.offsetType = offsetType;
    }

    public String getPlanLevelId() {
        return this.planLevelId;
    }

    public void setPlanLevelId(String planLevelId) {
        this.planLevelId = planLevelId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStartEff() {
        return this.startEff;
    }

    public void setStartEff(String startEff) {
        this.startEff = startEff;
    }

}
