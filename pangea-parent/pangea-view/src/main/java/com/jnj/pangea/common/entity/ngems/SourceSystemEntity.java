package com.jnj.pangea.common.entity.ngems;

import com.jnj.pangea.common.CommonEntity;

public class SourceSystemEntity extends CommonEntity {

    private String localSourceSystem;
    private String localSourceSystemName;
    private String sourceSystem;
    private String sourceSystemName;

    public String getLocalSourceSystem() {
        return localSourceSystem;
    }

    public void setLocalSourceSystem(String localSourceSystem) {
        this.localSourceSystem = localSourceSystem;
    }

    public String getLocalSourceSystemName() {
        return localSourceSystemName;
    }

    public void setLocalSourceSystemName(String localSourceSystemName) {
        this.localSourceSystemName = localSourceSystemName;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceSystemName() {
        return sourceSystemName;
    }

    public void setSourceSystemName(String sourceSystemName) {
        this.sourceSystemName = sourceSystemName;
    }
}
