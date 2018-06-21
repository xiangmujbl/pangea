package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public class EDMSourceSystemV1Entity extends CommonEntity {

    private String sourceSystem;
    private String sourceSystemName;
    private String localSourceSystemName;
    private String localSourceSystem;
    private String sourceSystemType;

    public EDMSourceSystemV1Entity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setSourceSystemName((String) map.get("sourceSystemName"));
        setLocalSourceSystem((String) map.get("localSourceSystem"));
        setLocalSourceSystemName((String) map.get("localSourceSystemName"));
        setSourceSystemType((String) map.get("sourceSystemType"));
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

    public String getLocalSourceSystemName() {
        return localSourceSystemName;
    }

    public void setLocalSourceSystemName(String localSourceSystemName) {
        this.localSourceSystemName = localSourceSystemName;
    }

    public String getLocalSourceSystem() {
        return localSourceSystem;
    }

    public void setLocalSourceSystem(String localSourceSystem) {
        this.localSourceSystem = localSourceSystem;
    }

	public String getSourceSystemType() {
		return sourceSystemType;
	}

	public void setSourceSystemType(String sourceSystemType) {
		this.sourceSystemType = sourceSystemType;
	}
    
}
