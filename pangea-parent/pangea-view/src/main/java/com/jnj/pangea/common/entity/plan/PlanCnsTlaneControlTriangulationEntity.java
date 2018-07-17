package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsTlaneControlTriangulationEntity extends CommonEntity {

    private String sequenceNumber;
    private String tlaneName;
    private String stepNumber;
    private String originLocation;
    private String destinatonLocation;

    public PlanCnsTlaneControlTriangulationEntity(Map<String, Object> map) {
        super(map);

        setSequenceNumber((String) map.get("sequenceNumber"));
        setTlaneName((String) map.get("tlaneName"));
        setStepNumber((String) map.get("stepNumber"));
        setOriginLocation((String) map.get("originLocation"));
        setDestinatonLocation((String) map.get("destinationLocation"));
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

    public String getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this.stepNumber = stepNumber;
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
}
