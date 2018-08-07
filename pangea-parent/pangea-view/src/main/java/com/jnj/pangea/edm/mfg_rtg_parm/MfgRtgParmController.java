package com.jnj.pangea.edm.mfg_rtg_parm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.inner.StringInner;
import com.jnj.pangea.hook.EDMMfgRtgParmHook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class MfgRtgParmController implements IEventProcessor {

	private final String FAILREGION = "/plan/edm_failed_data";

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
				.filter(e -> !(EDMMfgRtgParmHook.processmfgrtgparm(e.getValue())))
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
											.append("srcSysCd",
													data.get("srcSysCd"))
											.append("rtgTypeCd",
													data.get("rtgTypeCd"))
											.append("rtgGrpCd",
													data.get("rtgGrpCd"))
											.append("rtgNodeNum",
													data.get("rtgNodeNum"))
											.append("intrnlSubCalcNum",
													data.get("intrnlSubCalcNum"))
											.append("intrnlPrcsInstrNum",
													data.get("intrnlPrcsInstrNum"))
											.append("intrnlPrcsInstrCharValNum",
													data.get("intrnlPrcsInstrCharValNum"))
											.append("mfgParmVersCntrNbr",
													data.get("mfgParmVersCntrNbr"))
											.toJson();

									ViewResultItem viewRaw = ViewResultBuilder
											.newResultItem(viewKey, data);
									resultList.add(viewRaw);
								} else {
									failMap.forEach((key, value) -> {
										ViewResultItem viewRaw = ViewResultBuilder
												.newResultItem(
														FAILREGION,
														key,
														(Map<String, Object>) value
																.toRawData());
										resultList.add(viewRaw);
									});
								}
							} catch (Exception exception) {
								LogUtil.getCoreLog()
										.info("MfgRtgParmController Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"MfgRtgParmController Exception:",
										exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String project_one = "project_one";
		String plnty = StringInner.getString(map, "plnty");
		String plnnr = StringInner.getString(map, "plnnr");
		String plnkn = StringInner.getString(map, "plnkn");
		String plnph = StringInner.getString(map, "plnph");
		String plnft = StringInner.getString(map, "plnft");
		String mkmzl = StringInner.getString(map, "mkmzl");
		String zaehl = StringInner.getString(map, "zaehl");
		String datuv = StringInner.getString(map, "datuv");
		String loekz = StringInner.getString(map, "loekz");
		String aennr = StringInner.getString(map, "aennr");
		String andat = StringInner.getString(map, "andat");
		String aedat = StringInner.getString(map, "aedat");
		String atinn = StringInner.getString(map, "atinn");
		String atwrt = StringInner.getString(map, "atwrt");
		String sourceSystem = null;

		//rtgGrpCd
		String rtgGrpCd = StringInner.getString(map, "plnnr");
		builder.put("rtgGrpCd", rtgGrpCd);
		//rtgNodeNum
		String rtgNodeNum = StringInner.getString(map, "plnkn");
		builder.put("rtgNodeNum", rtgNodeNum);
		//intrnlSubCalcNum
		String intrnlSubCalcNum = StringInner.getString(map, "plnph");
		builder.put("intrnlSubCalcNum", intrnlSubCalcNum);
		//intrnlPrcsInstrNum
		String intrnlPrcsInstrNum = StringInner.getString(map, "plnft");
		builder.put("intrnlPrcsInstrNum", intrnlPrcsInstrNum);
		//intrnlPrcsInstrCharValNum
		String intrnlPrcsInstrCharValNum = StringInner.getString(map, "mkmzl");
		builder.put("intrnlPrcsInstrCharValNum", intrnlPrcsInstrCharValNum);
		//mfgParmVersCntrNbr
		String mfgParmVersCntrNbr = StringInner.getString(map, "zaehl");
		builder.put("mfgParmVersCntrNbr", mfgParmVersCntrNbr);
		//vldFromDt
		String vldFromDt = StringInner.getString(map, "datuv");
		builder.put("vldFromDt", vldFromDt);
		//delInd
		String delInd = StringInner.getString(map, "loekz");
		builder.put("delInd", delInd);
		//chgNum
		String chgNum = StringInner.getString(map, "aennr");
		builder.put("chgNum", chgNum);
		//crtDttm
		String crtDttm = StringInner.getString(map, "andat");
		builder.put("crtDttm", crtDttm);
		//chgDttm
		String chgDttm = StringInner.getString(map, "aedat");
		builder.put("chgDttm", chgDttm);
		//charCd
		String charCd = StringInner.getString(map, "atinn");
		builder.put("charCd", charCd);
		//charVal
		String charVal = StringInner.getString(map, "atwrt");
		builder.put("charVal", charVal);

		String srcSysCd = null;

		Map map1 = T_1(project_one);
		if (map1 != null) {
			sourceSystem = StringInner.getString(map1, "sourceSystem");
		}

		builder.put("srcSysCd", sourceSystem);

		String rtgTypeCd = null;

		builder.put("rtgTypeCd", plnty);

		return true;
	}

	public Map T_1(String project_one) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria(
				"localSourceSystem").is(project_one);
		ADFCriteria groupCriteria7 = adfCriteria1;

		ADFCriteria adfCriteria = groupCriteria7;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/source_system_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map J1_2(String plnty, String plnnr, String plnkn, String plnph) {

		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("plnty").is(plnty);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("plnnr").is(plnnr);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("plnkn").is(plnkn);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("plnph").is(plnph);
		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("topnr").is("10");
		ADFCriteria groupCriteria8 = adfCriteria6.and(adfCriteria5)
				.and(adfCriteria4).and(adfCriteria3).and(adfCriteria2);

		ADFCriteria adfCriteria = groupCriteria8;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/project_one/plph", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	private void writeFailDataToRegion(Map<String, RawDataBuilder> failMap,
			String functionalArea, String interfaceID, String errorCode,
			String errorValue, String sourceSystem, String key1, String key2,
			String key3, String key4, String key5, String businessArea) {
		String keyJson = JsonObject.create()
				.append("functionalArea", functionalArea)
				.append("interfaceID", interfaceID)
				.append("errorCode", errorCode)
				.append("sourceSystem", sourceSystem).append("key1", key1)
				.append("key2", key2).append("key3", key3).append("key4", key4)
				.append("key5", key5).toJson();

		RawDataBuilder failBuilder = new RawDataBuilder();
		failBuilder.put("functionalArea", functionalArea);
		failBuilder.put("interfaceID", interfaceID);
		failBuilder.put("errorCode", errorCode);
		failBuilder.put("sourceSystem", sourceSystem);
		failBuilder.put("key1", key1);
		failBuilder.put("key2", key2);
		failBuilder.put("key3", key3);
		failBuilder.put("key4", key4);
		failBuilder.put("key5", key5);
		failBuilder.put("businessArea", businessArea);
		failBuilder.put("errorValue", errorValue);

		failMap.put(keyJson, failBuilder);
	}

}
