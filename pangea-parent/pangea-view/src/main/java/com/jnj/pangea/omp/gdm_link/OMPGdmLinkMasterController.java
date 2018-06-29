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
public class OMPGdmLinkMasterController implements IEventProcessor {

	private final String FAILREGION = "";

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
				.filter(e -> !(OMPGdmLinkMasterHook.check(e.getValue())))
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
										.info("OMPGdmLinkMasterController Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog()
										.info("OMPGdmLinkMasterController Exception:",
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

		String plntCd = String.valueOf(map.get("plntCd"));

		String endEff1 = "";

		String rtngSqncNum = "";

		String rtngSqncCtgryCd = "";

		String srcSysCd = String.valueOf(map.get("srcSysCd"));

		String rtgItemEndDate = "";

		String operNum = "";

		String rtngGrpCntrNbr = "";

		String plntCd_versn = "";

		String valFromDt = String.valueOf(map.get("valFromDt"));

		String matlNum_versn = "";

		String valToDt = String.valueOf(map.get("valToDt"));

		String rntgTypCd = "";

		String rtngGrpCntrNum = String.valueOf(map.get("rtngGrpCntrNum"));

		String rtngNdeNum = "";

		String matlNum = String.valueOf(map.get("matlNum"));

		String rntgGrpCd = "";

		String rntgGrpCntrNum = "";

		String rtngTypCd = String.valueOf(map.get("rtngTypCd"));

		String rtngGrpCd = String.valueOf(map.get("rtngGrpCd"));

		String valFromDt_versn = "";

		String prdntVrsnNum = String.valueOf(map.get("prdntVrsnNum"));

		String rtngItmNum = "";

		valFromDt_versn = valFromDt;
		matlNum_versn = matlNum;
		plntCd_versn = plntCd;

		List<Map.Entry<String, String>> matlMfgRtngList = null;
		matlMfgRtngList = ruleJ1_2(srcSysCd, rtngTypCd, rtngGrpCd,
				rtngGrpCntrNum);
		if (matlMfgRtngList != null && matlMfgRtngList.size() > 0) {
			for (Map.Entry<String, String> matlMfgRtngListEntry : matlMfgRtngList) {
				Map<String, Object> matlMfgRtngListMap = JsonObject.append(
						matlMfgRtngListEntry.getValue()).toMap();
				srcSysCd = String.valueOf(matlMfgRtngListMap.get("srcSysCd"));
				matlNum = String.valueOf(matlMfgRtngListMap.get("matlNum"));
				rtngTypCd = String.valueOf(matlMfgRtngListMap.get("rtngTypCd"));
				rntgGrpCd = String.valueOf(matlMfgRtngListMap.get("rntgGrpCd"));
				rntgGrpCntrNbr = String.valueOf(matlMfgRtngListMap
						.get("rntgGrpCntrNbr"));

				List<Map.Entry<String, String>> mfgRtngSeqList = null;
				mfgRtngSeqList = ruleJ1_3(srcSysCd, rtngTypCd, rntgGrpCd,
						rntgGrpCntrNbr);
				if (mfgRtngSeqList != null && mfgRtngSeqList.size() > 0) {
					for (Map.Entry<String, String> mfgRtngSeqListEntry : mfgRtngSeqList) {
						Map<String, Object> mfgRtngSeqListMap = JsonObject
								.append(mfgRtngSeqListEntry.getValue()).toMap();
						srcSysCd = String.valueOf(mfgRtngSeqListMap
								.get("srcSysCd"));
						rtngTypCd = String.valueOf(mfgRtngSeqListMap
								.get("rtngTypCd"));
						rtngGrpCd = String.valueOf(mfgRtngSeqListMap
								.get("rtngGrpCd"));
						rtngGrpCntrNbr = String.valueOf(mfgRtngSeqListMap
								.get("rtngGrpCntrNbr"));
						rtngSqncNum = String.valueOf(mfgRtngSeqListMap
								.get("rtngSqncNum"));
						rtngSqncCtgryCd = String.valueOf(mfgRtngSeqListMap
								.get("rtngSqncCtgryCd"));

						List<Map.Entry<String, String>> mfgRtngItmNdeList = null;
						mfgRtngItmNdeList = ruleJ1_4(srcSysCd, rtngTypCd,
								rtngGrpCd, rtngGrpCntrNbr, rtngSqncNum);
						if (mfgRtngItmNdeList != null
								&& mfgRtngItmNdeList.size() > 0) {
							for (Map.Entry<String, String> mfgRtngItmNdeListEntry : mfgRtngItmNdeList) {
								Map<String, Object> mfgRtngItmNdeListMap = JsonObject
										.append(mfgRtngItmNdeListEntry
												.getValue()).toMap();
								srcSysCd = String.valueOf(mfgRtngItmNdeListMap
										.get("srcSysCd"));
								rtngTypCd = String.valueOf(mfgRtngItmNdeListMap
										.get("rtngTypCd"));
								rtngGrpCd = String.valueOf(mfgRtngItmNdeListMap
										.get("rtngGrpCd"));
								rtngGrpCntrNbr = String
										.valueOf(mfgRtngItmNdeListMap
												.get("rtngGrpCntrNbr"));
								rtngSqncNum = String
										.valueOf(mfgRtngItmNdeListMap
												.get("rtngSqncNum"));
								rtngNdeNum = String
										.valueOf(mfgRtngItmNdeListMap
												.get("rtngNdeNum"));

								List<Map.Entry<String, String>> mfgRtngItmList = null;
								mfgRtngItmList = ruleJ1_5(srcSysCd, rtngTypCd,
										rtngGrpCd, rtngNdeNum);
								if (mfgRtngItmList != null
										&& mfgRtngItmList.size() > 0) {
									for (Map.Entry<String, String> mfgRtngItmListEntry : mfgRtngItmList) {
										Map<String, Object> mfgRtngItmListMap = JsonObject
												.append(mfgRtngItmListEntry
														.getValue()).toMap();
										srcSysCd = String
												.valueOf(mfgRtngItmListMap
														.get("srcSysCd"));
										rtngTypCd = String
												.valueOf(mfgRtngItmListMap
														.get("rtngTypCd"));
										rtngGrpCd = String
												.valueOf(mfgRtngItmListMap
														.get("rtngGrpCd"));
										rtngItmNum = String
												.valueOf(mfgRtngItmListMap
														.get("rtngItmNum"));
										operNum = String
												.valueOf(mfgRtngItmListMap
														.get("operNum"));
										valFromDt = String
												.valueOf(mfgRtngItmListMap
														.get("valFromDt"));
										rtgItemEndDate = String
												.valueOf(mfgRtngItmListMap
														.get("rtgItemEndDate"));
										rtngSqncNum = String
												.valueOf(mfgRtngItmListMap
														.get("rtngSqncNum"));

										RawDataBuilder builder = new RawDataBuilder();

										builder.put("active", "YES");
										builder.put("activeOPRERP", "YES");
										builder.put("activeOPROMP", "YES");
										builder.put("activeSOPERP", "YES");
										builder.put("activeSOPOMP", "NO");
										builder.put("comments", "");
										builder.put("firstOccupied", "");
										builder.put("planLevelId", "*");
										builder.put("secondOccupied", "");

										String linkId = null;

										if (mfgRtngItmNdeList != null
												&& mfgRtngItmNdeList.size() > 0
												&& mfgRtngItmList != null
												&& mfgRtngItmList.size() > 0) {

											linkId = OMPGdmLinkMasterHook
													.getLinkId(prdntVrsnNum,
															matlNum_versn,
															plntCd_versn,
															mfgRtngItmNdeList,
															operNum,
															rtngSqncNum,
															rtngNdeNum,
															mfgRtngItmList,
															rtngSqncCtgryCd);
										}

										else {

											return false;
										}
										builder.put("linkId", linkId);

										String endEff = null;

										endEff = OMPGdmLinkMasterHook
												.getEndEff_T5(rtgItemEndDate,
														valToDt);

										builder.put("endEff", endEff);

										String operationId = null;

										if (StringInner
												.isStringNotEmpty(operNum)) {

											operationId = StringInner.join(
													prdntVrsnNum, "/",
													matlNum_versn, "/",
													plntCd_versn, "/", operNum);
										}

										builder.put("operationId", operationId);

										String prevOperationId = null;

										if (mfgRtngItmNdeList != null
												&& mfgRtngItmNdeList.size() > 0) {

											prevOperationId = OMPGdmLinkMasterHook
													.getPrevOperationId(
															prdntVrsnNum,
															matlNum_versn,
															plntCd_versn,
															mfgRtngItmNdeList,
															rtngNdeNum,
															operNum,
															mfgRtngItmList,
															rtngSqncCtgryCd);
										}

										else {

											return false;
										}
										builder.put("prevOperationId",
												prevOperationId);

										String processId = null;

										if (StringInner
												.isStringNotEmpty(prdntVrsnNum)
												&& StringInner
														.isStringNotEmpty(matlNum_versn)
												&& StringInner
														.isStringNotEmpty(plntCd_versn)) {

											processId = StringInner.join(
													prdntVrsnNum, "/",
													matlNum_versn, "/",
													plntCd_versn);
										}

										builder.put("processId", processId);

										String startEff = null;

										startEff = OMPGdmLinkMasterHook
												.getStartEff_T10(valFromDt,
														valFromDt_versn);

										builder.put("startEff", startEff);

										rawDataBuilderList.add(builder);
									}
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	public String ruleJ1_1(String srcSysCd) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria groupCriteria19 = adfCriteria1;

		ADFCriteria adfCriteria = groupCriteria19;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_plan_parameter", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
		}

		return null;

	}

	public List ruleJ1_2(String srcSysCd, String rtngTypCd, String rtngGrpCd,
			String rtngGrpCntrNum) {

		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("rntgGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("rntgGrpCntrNbr")
				.is(rtngGrpCntrNum);
		ADFCriteria groupCriteria20 = adfCriteria5.and(adfCriteria4)
				.and(adfCriteria3).and(adfCriteria2);

		ADFCriteria adfCriteria = groupCriteria20;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/matl_mfg_rtng", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public List ruleJ1_3(String srcSysCd, String rtngTypCd, String rntgGrpCd,
			String rntgGrpCntrNbr) {

		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rntgGrpCd);
		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("rtngGrpCntrNbr")
				.is(rntgGrpCntrNbr);
		ADFCriteria groupCriteria21 = adfCriteria9.and(adfCriteria8)
				.and(adfCriteria7).and(adfCriteria6);

		ADFCriteria adfCriteria = groupCriteria21;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_rtng_seq", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			retList.sort((e1, e2) -> JsonObject
					.append(e1.getValue())
					.getValue("rtngSqncNum")
					.compareTo(
							JsonObject.append(e2.getValue()).getValue(
									"rtngSqncNum")));
			return retList;
		}

		return new ArrayList<>();

	}

	public List ruleJ1_4(String srcSysCd, String rtngTypCd, String rtngGrpCd,
			String rtngGrpCntrNbr, String rtngSqncNum) {

		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("rtngGrpCntrNbr")
				.is(rtngGrpCntrNbr);
		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria("rtngSqncNum")
				.is(rtngSqncNum);
		ADFCriteria groupCriteria22 = adfCriteria14.and(adfCriteria13)
				.and(adfCriteria12).and(adfCriteria11).and(adfCriteria10);

		ADFCriteria adfCriteria = groupCriteria22;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_rtng_itm_nde", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			retList.sort((e1, e2) -> JsonObject
					.append(e1.getValue())
					.getValue("rtngNdeNum")
					.compareTo(
							JsonObject.append(e2.getValue()).getValue(
									"rtngNdeNum")));
			return retList;
		}

		return new ArrayList<>();

	}

	public List ruleJ1_5(String srcSysCd, String rtngTypCd, String rtngGrpCd,
			String rtngNdeNum) {

		ADFCriteria adfCriteria15 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria16 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("rtngItmNum").is(
				rtngNdeNum);
		ADFCriteria groupCriteria23 = adfCriteria18.and(adfCriteria17)
				.and(adfCriteria16).and(adfCriteria15);

		ADFCriteria adfCriteria = groupCriteria23;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_rtng_itm", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			retList.sort((e1, e2) -> JsonObject
					.append(e1.getValue())
					.getValue("operNum")
					.compareTo(
							JsonObject.append(e2.getValue())
									.getValue("operNum")));
			return retList;
		}

		return new ArrayList<>();

	}

}
