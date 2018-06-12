package com.jnj.pangea.omp.gdm_transport.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceListV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.CnsTlaneItemExceptionDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlnSplLocDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProcessTypeDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_transport.bo.OMPGdmTransportBo;

import java.util.*;

public abstract class OMPGdmTransportServiceParent {

	boolean curationSkip;
	boolean curationFail;
	String curationSkipReason;
	protected List<Map<String, String>> failedRules;


    public abstract ResultObject buildView(String key, Object o, Object o2);

	/**
	 * Get location string as list
	 * @param location
	 * @return
	 */
	protected ArrayList<String> getLocationStringArray (String location) {
		ArrayList<String> locArrayList = new ArrayList<>();
		if (location.contains("_V_")) {
			locArrayList = new ArrayList<>(Arrays.asList(location.split("_V_")));
		} else {
			ArrayList<String> tempLocList = new ArrayList<>(Arrays.asList(location.split("_")));
			String plant = tempLocList.remove(tempLocList.size()-1);
			String src = String.join("_",tempLocList);
			locArrayList.add(src);
			locArrayList.add(plant);
		}

		//check for 3rd scenario, vendor id at end
		if (locArrayList.get(1).contains("$")) {
			String[] tmp = locArrayList.get(1).split("\\$");
			String plant = tmp[0];
			String vendor = tmp[1];
			locArrayList.set(1, plant);
			locArrayList.add(vendor);
		}

		return locArrayList;
	}

	/**
	 * Take the plant number from location string list
	 * @param location
	 * @return
	 */
	protected String getLocalPlantNum (String location) {
		ArrayList<String> locList = getLocationStringArray(location);
		return locList.get(1);
	}

	/**
	 * Take the source system from the location string list
	 * @param location
	 * @return
	 */
	protected String getSourceSystem (String location) {
		ArrayList<String> locList = getLocationStringArray(location);
		return locList.get(0);
	}


	protected String getVendorId (String location) {
		ArrayList<String> locList = getLocationStringArray(location);
		String vendorId="";
		if( locList != null && locList.size() > 2 && locList.get(2) != null )
			vendorId=locList.get(2).trim();

		return vendorId;
	}
	/**
	 * set the class's failed rule list and flag
	 * @param rule
	 * @param msg
	 */
	protected void setFailedRule (String rule, String msg) {
		this.curationFail = true;
		Map<String,String> error = new HashMap<>();
		error.put("rule", rule);
		error.put("error", msg);
		this.failedRules.add(error);
	}

}
