package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsTlaneControlEntity extends CommonEntity {

    private String sequenceNumber;
    private String tlaneName;
    private String validFrom;
    private String validTo;
    private String originLocation;
    private String destinationLocation;
    private String mode;
    private String leadTime;
    private String processTypeId;
    private String sourceSystemCriticalParameters;
    private String trigSysPlant;
    private String triangulationDetail;
    private String trigSysTransaction;


    public PlanCnsTlaneControlEntity(Map<String, Object> map) {
        super(map);

        setSequenceNumber((String) map.get("sequenceNumber"));
        setTlaneName((String) map.get("tlaneName"));
        setValidFrom((String) map.get("validFrom"));
        setValidTo((String) map.get("validTo"));
        setOriginLocation((String) map.get("originLocation"));
        setDestinationLocation((String) map.get("destinationLocation"));
        setMode((String) map.get("mode"));
        setLeadTime((String) map.get("leadTime"));
        setProcessTypeId((String) map.get("processTypeId"));
        setSourceSystemCriticalParameters((String) map.get("sourceSystemCriticalParameters"));
        setTrigSysPlant((String) map.get("trigSysPlant"));
        setTriangulationDetail((String) map.get("triangulationDetail"));
        setTrigSysTransaction((String) map.get("trigSysTransaction"));
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

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
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

    public String getSourceSystemCriticalParameters() {
        return sourceSystemCriticalParameters;
    }

    public void setSourceSystemCriticalParameters(String sourceSystemCriticalParameters) {
        this.sourceSystemCriticalParameters = sourceSystemCriticalParameters;
    }

    public String getTrigSysPlant() {
        return trigSysPlant;
    }

    public void setTrigSysPlant(String trigSysPlant) {
        this.trigSysPlant = trigSysPlant;
    }

    public String getTriangulationDetail() {
        return triangulationDetail;
    }

    public void setTriangulationDetail(String triangulationDetail) {
        this.triangulationDetail = triangulationDetail;
    }

    public String getTrigSysTransaction() {
        return trigSysTransaction;
    }

    public void setTrigSysTransaction(String trigSysTransaction) {
        this.trigSysTransaction = trigSysTransaction;
    }
}