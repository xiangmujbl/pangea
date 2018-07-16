package com.jnj.pangea.omp.gdm_machine_subcon.Controller;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.actors.remote.CurationRawDataHelper;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.adf.client.api.query.QueryHelper;
import org.apache.commons.lang3.StringUtils;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.inner.DateInner;
import com.jnj.inner.StringInner;

import java.math.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.io.*;
import java.nio.*;

@SuppressWarnings("unchecked")
public class GdmMachineSubConController implements IEventProcessor {

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
				.forEach(
						e -> {
							try {
								RawDataBuilder builder = new RawDataBuilder();

								Map<String, RawDataBuilder> failMap = new HashMap<>();

								List<ViewResultItem> itemList = new ArrayList<>();

								boolean isOk = buildView(e.getValue(), builder,
										failMap, itemList);
								if (itemList != null && itemList.size() > 0) {
									resultList.addAll(itemList);
								}

								if (isOk) {
									Map<String, Object> data = (Map<String, Object>) builder
											.toRawData();
									String viewKey = JsonObject
											.create()
											.append("machineId",
													data.get("machineId"))
											.toJson();

									ViewResultItem viewRaw = ViewResultBuilder
											.newResultItem(viewKey, data);
									resultList.add(viewRaw);
								}
							} catch (Exception exception) {
								LogUtil.getCoreLog()
										.info("GdmMachineSubConController Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog()
										.info("GdmMachineSubConController Exception:",
												exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String sourceSystem = StringInner.getString(map, "sourceSystem");
		String localPlant = StringInner.getString(map, "localPlant");
		String specLocAtt3 = StringInner.getString(map, "specLocAtt3");
		String specLocAttDesc3 = StringInner.getString(map, "specLocAttDesc3");

		String machineId = null;

		if (StringInner.isStringNotEmpty(sourceSystem)) {

			Map cnsPlanParameterMap = joinCnsPlanParameter(sourceSystem);
			if (cnsPlanParameterMap != null) {
				if (StringInner.isStringNotEmpty(localPlant)) {
					Map cnsPlantAttrMap = joinCnsPlantAttr(sourceSystem,
							localPlant);
					if (cnsPlantAttrMap != null) {
						if (StringInner.isStringNotEmpty(specLocAttDesc3)) {
							machineId = StringInner.join(sourceSystem, "_",
									localPlant, "/", specLocAttDesc3);
						} else {
							return false;
						}
					}
					if (cnsPlantAttrMap == null) {
						return false;
					}
				} else {
					return false;
				}
			}
			if (cnsPlanParameterMap == null) {
				return false;
			}
		}

		else {

			return false;
		}
		machineId = machineId;
		builder.put("machineId", machineId);

		String active = null;

		builder.put("active", "YES");

		String activeOPRERP = null;

		builder.put("activeOPRERP", "YES");

		String activeSOPERP = null;

		builder.put("activeSOPERP", "NO");

		String building = null;

		builder.put("building", "");

		String description = null;

		description = StringInner
				.join("Dummy Subcon W/C", " ", specLocAttDesc3);
		builder.put("description", description);

		String locationId = null;

		locationId = StringInner.join(sourceSystem, "_", localPlant);
		builder.put("locationId", locationId);

		String machineCapacity = null;

		builder.put("machineCapacity", "infinite");

		String machineTypeId = null;

		builder.put("machineTypeId", "Production");

		String parentMachineId = null;

		builder.put("parentMachineId", "");

		return true;
	}

	public Map joinCnsPlanParameter(String srcSysCd) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("attribute").is(
				srcSysCd);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("dataObject").is(
				"SEND_TO_OMP");
		ADFCriteria groupCriteria7 = adfCriteria3.and(adfCriteria2).and(
				adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria7;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_plan_parameter", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map joinCnsPlantAttr(String sourceSystem, String localPlant) {

		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("localPlant").is(
				localPlant);
		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria(
				"localPlanningRelevant").is("X");
		ADFCriteria groupCriteria8 = adfCriteria6.and(adfCriteria5).and(
				adfCriteria4);

		ADFCriteria adfCriteria = groupCriteria8;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_plant_attr", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

}
