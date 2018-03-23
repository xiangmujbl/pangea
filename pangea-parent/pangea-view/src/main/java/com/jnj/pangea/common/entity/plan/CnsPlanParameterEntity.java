package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsPlanParameterEntity extends CommonEntity {

    private String sourceSystem;
    private String dataObject;
    private String attribute;
    private String parameter;
    private String inclExcl;
    private String parameterValue;
    public CnsPlanParameterEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setDataObject((String) map.get("dataObject"));
        setAttribute((String) map.get("attribute"));
        setParameter((String) map.get("parameter"));
        setInclExcl((String) map.get("inclExcl"));
        setParameterValue((String)map.get("parameterValue"));
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDataObject() {
        return dataObject;
    }

    public void setDataObject(String dataObject) {
        this.dataObject = dataObject;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getInclExcl() {
        return inclExcl;
    }

    public void setInclExcl(String inclExcl) {
        this.inclExcl = inclExcl;
    }
}
