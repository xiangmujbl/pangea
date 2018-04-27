package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsTlaneItemEntity extends CommonEntity {

    private String validTo;
    private String originLocation;
    private String materialNumber;
    private String processTypeId;
    private String validFrom;
    private String destinationLocation;
    private String leadTime;
    private String mode;
    private String sequenceNumber;
    private String tlaneName;



    public CnsTlaneItemEntity (Map<String, Object> map) {
        super(map);
        setValidTo((String) map.get("validTo"));
        setOriginLocation((String) map.get("originLocation"));
        setMaterialNumber((String) map.get("materialNumber"));
        setProcessTypeId((String) map.get("processTypeId"));
        setValidFrom((String) map.get("validFrom"));
        setDestinationLocation((String) map.get("destinationlocation"));
        setLeadTime((String) map.get("leadtime"));
        setMode((String) map.get("mode"));
        setMode((String) map.get("sequenceNumber"));
        setMode((String) map.get("tlaneName"));
    }

    public String getSequenceNumber () {
        return sequenceNumber;
    }

    public void setSequenceNumber (String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getTlaneName () {
        return tlaneName;
    }

    public void setTlaneName (String tlaneName) {
        this.tlaneName = tlaneName;
    }

    public String getValidTo () {
        return validTo;
    }

    public void setValidTo (String validTo) {
        this.validTo = validTo;
    }

    public String getOriginLocation () {
        return originLocation;
    }

    public void setOriginLocation (String originLocation) {
        this.originLocation = originLocation;
    }

    public String getMaterialNumber () {
        return materialNumber;
    }

    public void setMaterialNumber (String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLeadTime () {
        return leadTime;
    }

    public void setLeadTime (String leadTime) {
        this.leadTime = leadTime;
    }

    public String getMode () {
        return mode;
    }

    public void setMode (String mode) {
        this.mode = mode;
    }

    public String getProcessTypeId () {
        return processTypeId;
    }

    public void setProcessTypeId (String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getValidFrom () {
        return validFrom;
    }

    public void setValidFrom (String validFrom) {
        this.validFrom = validFrom;
    }

    public String getDestinationLocation () {
        return destinationLocation;
    }

    public void setDestinationLocation (String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }
}
