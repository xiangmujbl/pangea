package com.jnj.pangea.omp.gdm_link;

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
public class OMPGdmLinkProcessController implements IEventProcessor {

	private final String FAILREGION = "";

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
				.filter(e -> !(OMPGdmLinkProcessHook.check(e.getValue())))
				.forEach(
						e -> {
							try {
								List<RawDataBuilder> rawDataBuilderList = new ArrayList<>();
								Map<String, RawDataBuilder> failMap = new HashMap<>();
								boolean isOk = buildView(e.getValue(),
										rawDataBuilderList, failMap);

								rawDataBuilderList.forEach(rawDataBuilder -> {
									Map<String, Object> data = (Map<String, Object>) rawDataBuilder
											.toRawData();
									String viewKey = JsonObject
											.create()
											.append("linkId",
													data.get("linkId"))
											.toJson();

									ViewResultItem viewRaw = ViewResultBuilder
											.newResultItem(viewKey, data);
									resultList.add(viewRaw);
								});

								failMap.forEach((key, value) -> {
									ViewResultItem viewRawFail = ViewResultBuilder
											.newResultItem(FAILREGION, key,
													(Map<String, Object>) value
															.toRawData());
									resultList.add(viewRawFail);
								});

							} catch (Exception exception) {
								LogUtil.getCoreLog()
										.info("OMPGdmLinkProcessController Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog()
										.info("OMPGdmLinkProcessController Exception:",
												exception);
							}

						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw,
			List<RawDataBuilder> rawDataBuilderList,
			Map<String, RawDataBuilder> failMap) {

		Map map = raw.toMap();

		String rntgGrpCntrNbr = "";

		String ordrRtngNum = String.valueOf(map.get("ordrRtngNum"));

		String rtngGrpCntrNum = String.valueOf(map.get("rtngGrpCntrNum"));

		String ordrRtngCtrNum = "";

		String mfgOrdrNum = String.valueOf(map.get("mfgOrdrNum"));

		String rtngTypCd = String.valueOf(map.get("rtngTypCd"));

		String rtngGrpCd = String.valueOf(map.get("rtngGrpCd"));

		String rtngSqncNum = "";

		String rtngSqncCtgryCd = "";

		String srcSysCd = "";

		String sourceSysCd = String.valueOf(map.get("sourceSysCd"));

		String operNum = "";

		List<Map.Entry<String, String>> mfgOrderSeqList = null;
		mfgOrderSeqList = getOrderSeqEntityList(sourceSysCd, rtngTypCd,
				rtngGrpCd, rtngGrpCntrNum, ordrRtngNum);
		if (mfgOrderSeqList != null && mfgOrderSeqList.size() > 0) {
			for (Map.Entry<String, String> mfgOrderSeqListEntry : mfgOrderSeqList) {
				Map<String, Object> mfgOrderSeqListMap = JsonObject.append(
						mfgOrderSeqListEntry.getValue()).toMap();
				srcSysCd = String.valueOf(mfgOrderSeqListMap.get("srcSysCd"));
				ordrRtngNum = String.valueOf(mfgOrderSeqListMap
						.get("ordrRtngNum"));
				rntgGrpCntrNbr = String.valueOf(mfgOrderSeqListMap
						.get("rntgGrpCntrNbr"));
				rtngSqncNum = String.valueOf(mfgOrderSeqListMap
						.get("rtngSqncNum"));
				rtngSqncCtgryCd = String.valueOf(mfgOrderSeqListMap
						.get("rtngSqncCtgryCd"));
				ordrRtngCtrNum = String.valueOf(mfgOrderSeqListMap
						.get("ordrRtngCtrNum"));

				List<Map.Entry<String, String>> orderRtngList = null;
				orderRtngList = getOrderRtngList(ordrRtngNum, ordrRtngCtrNum,
						srcSysCd);
				if (orderRtngList != null && orderRtngList.size() > 0) {
					for (Map.Entry<String, String> orderRtngListEntry : orderRtngList) {
						Map<String, Object> orderRtngListMap = JsonObject
								.append(orderRtngListEntry.getValue()).toMap();
						srcSysCd = String.valueOf(orderRtngListMap
								.get("srcSysCd"));
						ordrRtngNum = String.valueOf(orderRtngListMap
								.get("ordrRtngNum"));
						ordrRtngCtrNum = String.valueOf(orderRtngListMap
								.get("ordrRtngCtrNum"));
						operNum = String.valueOf(orderRtngListMap
								.get("operNum"));

						RawDataBuilder builder = new RawDataBuilder();

						builder.put("active", "YES");
						builder.put("activeOPRERP", "YES");
						builder.put("activeOPROMP", "YES");
						builder.put("activeSOPERP", "YES");
						builder.put("activeSOPOMP", "NO");
						builder.put("comments", "");
						builder.put("endEff", "2998/12/31 23:59:59");
						builder.put("firstOccupied", "");
						builder.put("planLevelId", "*");
						builder.put("secondOccupied", "");
						builder.put("startEff", "1980/01/01 00:00:00");

						String linkId = null;

						if ((StringInner.isStringNotEmpty(operNum) && StringInner
								.isStringNotEmpty(ordrRtngCtrNum))) {

							linkId = OMPGdmLinkProcessHook.getLinkId(
									mfgOrdrNum, operNum, rtngSqncNum,
									orderRtngList, ordrRtngCtrNum);
						}

						else {

							return false;
						}
						builder.put("linkId", linkId);

						String operationId = null;

						if (StringInner.isStringNotEmpty(mfgOrdrNum)
								&& StringInner.isStringNotEmpty(operNum)) {

							operationId = StringInner.join("PRO", "/",
									mfgOrdrNum, "/", operNum);
						}

						else {

							return false;
						}
						builder.put("operationId", operationId);

						String prevOperationId = null;

						prevOperationId = OMPGdmLinkProcessHook
								.getPrevOperationId(mfgOrdrNum, orderRtngList,
										ordrRtngCtrNum);

						builder.put("prevOperationId", prevOperationId);

						String processId = null;

						if (StringInner.isStringNotEmpty(mfgOrdrNum)) {

							processId = StringInner
									.join("PRO", "/", mfgOrdrNum);
						}

						else {

							return false;
						}
						builder.put("processId", processId);

						rawDataBuilderList.add(builder);
					}
				}
			}
		}

		return true;
	}

	public List getOrderSeqEntityList(String sourceSysCd, String rtngTypCd,
			String rtngGrpCd, String rtngGrpCntrNum, String ordrRtngNum) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("srcSysCd").is(
				sourceSysCd);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("rntgGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("rntgGrpCntrNbr")
				.is(rtngGrpCntrNum);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("ordrRtngNum").is(
				ordrRtngNum);
		ADFCriteria groupCriteria9 = adfCriteria5.and(adfCriteria4)
				.and(adfCriteria3).and(adfCriteria2).and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria9;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_order_seq", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public List getOrderRtngList(String ordrRtngNum, String ordrRtngCtrNum,
			String srcSysCd) {

		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("ordrRtngNum").is(
				ordrRtngNum);
		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("ordrRtngCtrNum")
				.is(ordrRtngCtrNum);
		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria groupCriteria10 = adfCriteria8.and(adfCriteria7).and(
				adfCriteria6);

		ADFCriteria adfCriteria = groupCriteria10;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_order_rtng", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

}
