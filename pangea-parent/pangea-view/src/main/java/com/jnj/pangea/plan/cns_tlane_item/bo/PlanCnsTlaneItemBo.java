package com.jnj.pangea.plan.cns_tlane_item.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsTlaneItemBo extends BaseBo {

    private String sequenceNumber;
    private String tlaneName;
    private String validFrom;
    private String validTo;
    private String originLocation;
    private String destinatonLocation;
    private String materialNumber;
    private String mode;
    private String leadTime;
    private String processTypeId;


    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sequenceNumber", this.sequenceNumber)
                .add("tlaneName", this.tlaneName)
                .add("materialNumber",this.materialNumber)
                .toJsonString();
    }


    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getTlaneName() {
        return tlaneName;
    }

    public void setTlaneName(String tlaneName) {
        this.tlaneName = tlaneName;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    public String getDestinatonLocation() {
        return destinatonLocation;
    }

    public void setDestinatonLocation(String destinatonLocation) {
        this.destinatonLocation = destinatonLocation;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }
}
