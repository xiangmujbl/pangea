package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlanObjectFilterEntity extends CommonEntity {

    private String sourceObjectTechName;
    private String sourceSystem;
    private String sourceObjectAttribute1;
    private String sourceObjectAttribute1Value;
    private String sourceObjectAttribute2;
    private String sourceObjectAttribute2Value;
    private String inclusionExclusion;
    private String Comments;


    public PlanCnsPlanObjectFilterEntity(Map<String, Object> map) {
        super(map);
        setSourceObjectTechName((String) map.get("sourceObjectTechName"));
        setSourceSystem((String) map.get("sourceSystem"));
        setSourceObjectAttribute1((String) map.get("sourceObjectAttribute1"));
        setSourceObjectAttribute1Value((String) map.get("sourceObjectAttribute1Value"));
        setSourceObjectAttribute2((String) map.get("sourceObjectAttribute2"));
        setSourceObjectAttribute2Value((String) map.get("sourceObjectAttribute2Value"));
        setInclusionExclusion((String) map.get("inclusionExclusion"));
        setComments((String) map.get("Comments"));
    }

    public String getSourceObjectTechName() {
        return sourceObjectTechName;
    }

    public void setSourceObjectTechName(String sourceObjectTechName) {
        this.sourceObjectTechName = sourceObjectTechName;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceObjectAttribute1() {
        return sourceObjectAttribute1;
    }

    public void setSourceObjectAttribute1(String sourceObjectAttribute1) {
        this.sourceObjectAttribute1 = sourceObjectAttribute1;
    }

    public String getSourceObjectAttribute1Value() {
        return sourceObjectAttribute1Value;
    }

    public void setSourceObjectAttribute1Value(String sourceObjectAttribute1Value) {
        this.sourceObjectAttribute1Value = sourceObjectAttribute1Value;
    }

    public String getSourceObjectAttribute2() {
        return sourceObjectAttribute2;
    }

    public void setSourceObjectAttribute2(String sourceObjectAttribute2) {
        this.sourceObjectAttribute2 = sourceObjectAttribute2;
    }

    public String getSourceObjectAttribute2Value() {
        return sourceObjectAttribute2Value;
    }

    public void setSourceObjectAttribute2Value(String sourceObjectAttribute2Value) {
        this.sourceObjectAttribute2Value = sourceObjectAttribute2Value;
    }

    public String getInclusionExclusion() {
        return inclusionExclusion;
    }

    public void setInclusionExclusion(String inclusionExclusion) {
        this.inclusionExclusion = inclusionExclusion;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

}
