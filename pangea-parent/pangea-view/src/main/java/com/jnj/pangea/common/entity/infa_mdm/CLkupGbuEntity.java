package com.jnj.pangea.common.entity.infa_mdm;

import java.util.Map;

import com.jnj.pangea.common.entity.CommonEntity;

public class CLkupGbuEntity extends CommonEntity {

	private String gbuCd;

	private String gbuDescnTxt;

	public CLkupGbuEntity(Map<String, Object> map) {
        super(map);
        setGbuCd((String) map.get("gbuCd"));
        setGbuDescnTxt((String) map.get("gbuDescnTxt"));
    }

	public String getGbuCd() {
		return gbuCd;
	}

	public void setGbuCd(String gbuCd) {
		this.gbuCd = gbuCd;
	}
	public String getGbuDescnTxt() {
		return gbuDescnTxt;
	}
	public void setGbuDescnTxt(String gbuDescnTxt) {
		this.gbuDescnTxt = gbuDescnTxt;
	}
	@Override
	public String toString() {
		return "InfaMdmEntity [gbuCd=" + gbuCd + ", gbuDescnTxt=" + gbuDescnTxt + "]";
	}


}
