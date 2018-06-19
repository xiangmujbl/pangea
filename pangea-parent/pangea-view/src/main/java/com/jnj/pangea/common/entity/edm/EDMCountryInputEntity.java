package com.jnj.pangea.common.entity.edm;


import java.util.Map;

import com.jnj.pangea.common.entity.CommonEntity;

/**
 * Created by rtierne2 on 2018/5/10.
 */
public class EDMCountryInputEntity  extends CommonEntity{

    private String sourceSystem;
    private String localCountry;
    private String localCurrency;
  

    public EDMCountryInputEntity(Map<String, Object> map) {
		super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalCountry((String) map.get("localCountry"));
        setLocalCurrency((String) map.get("localCurrency"));

    }


	@Override
	public String toString() {
		return "EDMCountryInputEntity{" +
				"sourceSystem='" + sourceSystem + '\'' +
				", localCountry='" + localCountry + '\'' +
				", localCurrency='" + localCurrency + '\'' +
				'}';
	}

	public String getSourceSystem() {
		return sourceSystem;
	}


	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}


	public String getLocalCountry() {
		return localCountry;
	}


	public void setLocalCountry(String localCountry) {
		this.localCountry = localCountry;
	}


	public String getLocalCurrency() {
		return localCurrency;
	}


	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;
	}

  
}
