package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsTlaneControlTriangulationEntity extends CommonEntity {

    private String sequenceNumber;
    private String stepNumber;
    private String tlaneName;
    private String originLocation;
    private String destinationLocation;

    public PlanCnsTlaneControlTriangulationEntity(Map<String, Object> map) {
        super(map);
        setSequenceNumber((String) map.get("sequenceNumber"));
        setStepNumber((String) map.get("stepNumber"));
        setTlaneName((String) map.get("tlaneName"));
        setOriginLocation((String) map.get("originLocation"));
        setDestinationLocation((String) map.get("destinationLocaton"));
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getTlaneName() {
        return tlaneName;
    }

    public void setTlaneName(String tlaneName) {
        this.tlaneName = tlaneName;
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
}
