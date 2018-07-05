package com.jnj.pangea.common.entity.project_one;

import java.util.Map;

import com.jnj.pangea.common.entity.CommonEntity;

public class CrhsEntity extends CommonEntity {
	
	private String objtyHy;
	private String objidHy;
	private String objtyUp;
	private String objidUp;
	private String objtyHo;
	private String objidHo;
	private String objtyLe;
	private String objidLe;
	private String flgav;
	private String vcxpos;
	private String vcypos;
	private String mandt;

	public CrhsEntity(Map<String, Object> map) {
		super(map);
		
		setObjtyHy((String) map.get("objtyHy"));
		setObjidHy((String) map.get("objidHy"));
		setObjtyUp((String) map.get("objtyUp"));
		setObjidUp((String) map.get("objidUp"));
		setObjtyHo((String) map.get("objtyHo"));
		setObjidHo((String) map.get("objidHo"));
		setObjtyLe((String) map.get("objtyLe"));
		setObjidLe((String) map.get("objidLe"));
		setFlgav((String) map.get("flgav"));
		setVcxpos((String) map.get("vcxpos"));
		setVcypos((String) map.get("vcypos"));
		setMandt((String) map.get("mandt"));
	}

	public String getObjtyHy() {
		return objtyHy;
	}

	public void setObjtyHy(String objtyHy) {
		this.objtyHy = objtyHy;
	}

	public String getObjidHy() {
		return objidHy;
	}

	public void setObjidHy(String objidHy) {
		this.objidHy = objidHy;
	}

	public String getObjtyUp() {
		return objtyUp;
	}

	public void setObjtyUp(String objtyUp) {
		this.objtyUp = objtyUp;
	}

	public String getObjidUp() {
		return objidUp;
	}

	public void setObjidUp(String objidUp) {
		this.objidUp = objidUp;
	}

	public String getObjtyHo() {
		return objtyHo;
	}

	public void setObjtyHo(String objtyHo) {
		this.objtyHo = objtyHo;
	}

	public String getObjidHo() {
		return objidHo;
	}

	public void setObjidHo(String objidHo) {
		this.objidHo = objidHo;
	}

	public String getObjtyLe() {
		return objtyLe;
	}

	public void setObjtyLe(String objtyLe) {
		this.objtyLe = objtyLe;
	}

	public String getObjidLe() {
		return objidLe;
	}

	public void setObjidLe(String objidLe) {
		this.objidLe = objidLe;
	}

	public String getFlgav() {
		return flgav;
	}

	public void setFlgav(String flgav) {
		this.flgav = flgav;
	}

	public String getVcxpos() {
		return vcxpos;
	}

	public void setVcxpos(String vcxpos) {
		this.vcxpos = vcxpos;
	}

	public String getVcypos() {
		return vcypos;
	}

	public void setVcypos(String vcypos) {
		this.vcypos = vcypos;
	}

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

}
