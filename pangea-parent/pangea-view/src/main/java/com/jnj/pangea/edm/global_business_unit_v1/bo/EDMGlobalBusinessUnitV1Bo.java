package com.jnj.pangea.edm.global_business_unit_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMGlobalBusinessUnitV1Bo extends BaseBo{

	private String gbu;
	private String gbuName;
	
	@Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("gbu", this.gbu)
                .toJsonString();
    }
	public String getGbu() {
		return gbu;
	}
	public void setGbu(String gbu) {
		this.gbu = gbu;
	}
	public String getGbuName() {
		return gbuName;
	}
	public void setGbuName(String gbuName) {
		this.gbuName = gbuName;
	}
	@Override
	public String toString() {
		return "EdmGlobalBusinessUnitV1Entity [gbu=" + gbu + ", gbuName=" + gbuName + "]";
	}
	
	
}
