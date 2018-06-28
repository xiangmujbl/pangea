package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EdmMatInputEntity extends CommonEntity {


    private String localMaterialNumber;
    private String localTechnology;
    private String sourceSystem;

    public EdmMatInputEntity(Map<String, Object> map) {
        super(map);
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalTechnology((String) map.get("localTechnology"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }
    
    public String getLocalTechnology() {
		return localTechnology;
	}

	public void setLocalTechnology(String localTechnology) {
		this.localTechnology = localTechnology;
	}

	public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
}
