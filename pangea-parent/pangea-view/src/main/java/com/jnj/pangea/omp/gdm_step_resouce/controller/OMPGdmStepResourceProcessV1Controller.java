package com.jnj.pangea.omp.gdm_step_resouce.controller;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.inner.StringInner;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.omp.gdm_step_resouce.service.OMPGdmStepResourceProcessServiceV1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class OMPGdmStepResourceProcessV1Controller implements IEventProcessor {

	private final String FAILREGION = "";
	private OMPGdmStepResourceProcessServiceV1  service = OMPGdmStepResourceProcessServiceV1.getInstance();

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
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
											.append("stepResourceId",
													data.get("stepResourceId"))
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
										.info("OMPGdmStepResourceProcessV1Controller Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog()
										.info("OMPGdmStepResourceProcessV1Controller Exception:",
												exception);
							}

						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw,
			List<RawDataBuilder> rawDataBuilderList,
			Map<String, RawDataBuilder> failMap) {

		Map map = raw.toMap();
		LogUtil.getCoreLog().info("-------/edm/mfg_order-----Map>>" + map.toString());
		String sourceSysCd = map.get("sourceSysCd").toString();
		String actRlseDt = map.get("actRlseDt").toString();
		String mfgOrdrSttsCd = map.get("mfgOrdrSttsCd").toString();
		String plntCd = map.get("plntCd").toString();
		String mfgOrdrNum = map.get("mfgOrdrNum").toString();
		String ordrRtngNum = map.get("ordrRtngNum").toString();

		String yesDefault = "YES";
		String noDefault = "NO";
		String zeroDefault = "0";
		String prodStr = "production";

		if(StringInner.isStringNotEmpty(sourceSysCd) && StringInner.isStringNotEmpty(plntCd)
				&& StringInner.isStringNotEmpty(actRlseDt) && StringInner.isStringNotEmpty(mfgOrdrSttsCd)){
			if(service.checkCnsPlanParameter4J1_1(sourceSysCd)
					&& service.checkCnsPlantAttr(sourceSysCd,plntCd)
					&& service.checckCnsPlanParameter4J1_2(sourceSysCd,actRlseDt)
					&& service.checkCnsPlanParameter4J1_3(sourceSysCd,mfgOrdrSttsCd)
					&& service.checkCnsPlanParameter4J1_4(sourceSysCd, mfgOrdrSttsCd)){
				if(StringInner.isStringNotEmpty(mfgOrdrNum) && StringInner.isStringNotEmpty(ordrRtngNum)){
					Map mfgOrderItmMap = service.joinMfgOrderItm(sourceSysCd,mfgOrdrNum,plntCd);
					if(mfgOrderItmMap != null) {
						String srcSysCd = mfgOrderItmMap.get("srcSysCd").toString();
						String prdntVrsnNum = mfgOrderItmMap.get("prdntVrsnNum").toString();
						String matlNum = mfgOrderItmMap.get("matlNum").toString();
						plntCd = mfgOrderItmMap.get("plntCd").toString();
						if(StringInner.isStringNotEmpty(prdntVrsnNum) && StringInner.isStringNotEmpty(matlNum)){
							Map matlProdVersnMap = service.joinMaltProdVersn(srcSysCd,matlNum,plntCd,prdntVrsnNum);
							if(matlProdVersnMap != null){
								String minQuantity = "";
								srcSysCd = matlProdVersnMap.get("srcSysCd").toString();
								String rtngTypCd = matlProdVersnMap.get("rtngTypCd").toString();
								String rtngGrpCd = matlProdVersnMap.get("rtngGrpCd").toString();
								String rtngGrpCntrNum =matlProdVersnMap.get("rtngGrpCntrNum").toString();
								if(StringInner.isStringNotEmpty(srcSysCd)  && StringInner.isStringNotEmpty(rtngTypCd)
										&& StringInner.isStringNotEmpty(rtngGrpCd) && StringInner.isStringNotEmpty(rtngGrpCntrNum)){
									Map mfgRtngItmNde = service.joinMfgRtngItmNde(srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNum);
									if(mfgRtngItmNde != null){
										String rtngNdeNum = mfgRtngItmNde.get("rtngNdeNum").toString();
										Map mfgRtgParmMap = service.joinMfgRtgParm(srcSysCd,rtngTypCd,rtngGrpCd,rtngNdeNum);
										if(mfgRtgParmMap != null){
											String charVal = mfgRtgParmMap.get("charVal").toString();
											if(StringInner.isStringNotEmpty(charVal)){
												minQuantity = charVal;
											}
										}
									}
								}

								List<Map.Entry<String,String>> mfgOrderRtngMapmLst = service.joinMfgOrderRtng(sourceSysCd,ordrRtngNum);
								if(mfgOrderRtngMapmLst != null && mfgOrderRtngMapmLst.size() > 0){
									for (Map.Entry<String,String> mfgOrderRtngMap  : mfgOrderRtngMapmLst) {
										Map<String,Object> item = JsonObject.append(mfgOrderRtngMap.getValue()).toMap();
										String operCd = item.get("operCd").toString();
										String wrkCntrId = item.get("wrkCntrId").toString();
										String operNum = service.checkNull4Str(item.get("operNum").toString());
										if(StringInner.isStringNotEmpty(operCd)){
											if(service.checkT430(operCd) && StringInner.isStringNotEmpty(wrkCntrId)){
												Map wrkCtrMap = service.joinWrkCtr(srcSysCd,wrkCntrId);
												if(wrkCtrMap != null){
													srcSysCd = wrkCtrMap.get("srcSysCd").toString();
													String wrkCtrNum = wrkCtrMap.get("wrkCtrNum").toString();
													String wrkCtrTypeCd = wrkCtrMap.get("wrkCtrTypeCd").toString();
													String wrkCtrCd = service.checkNull4Str(wrkCtrMap.get("wrkCtrCd").toString());
													String plntCd4T3 = service.checkNull4Str(wrkCtrMap.get("plntCd").toString());
													if(StringInner.isStringNotEmpty(srcSysCd) && StringInner.isStringNotEmpty(wrkCtrNum)
															&& StringInner.isStringNotEmpty(wrkCtrTypeCd)){
														List<Map.Entry<String,String>> wrkCtrCapyMapLst = service.joinWrkCtrCapy(srcSysCd,wrkCtrNum,wrkCtrTypeCd);
														if(wrkCtrCapyMapLst != null && wrkCtrCapyMapLst.size()>0){
															Map capyHdr1 = null;
															Map capyHdr2 = null;
															for(Map.Entry<String,String> wrkCtrCapyMap : wrkCtrCapyMapLst){
																Map<String,Object> itm = JsonObject.append(wrkCtrCapyMap.getValue()).toMap();
																String capyNum = itm.get("capyNum").toString();
																srcSysCd = itm.get("srcSysCd").toString();
																if(StringInner.isStringNotEmpty(srcSysCd) && StringInner.isStringNotEmpty(capyNum)){
																	Map capyHdrMap = service.joinCapyHdr4T3(srcSysCd,capyNum);
																	if(capyHdrMap != null){
																		capyHdr1 = capyHdrMap;
																	}else{
																		capyHdrMap = service.joinCapyHdr4T7(srcSysCd,capyNum);
																		if(capyHdrMap != null){
																			capyHdr2 = capyHdrMap;
																		}
																	}
																}
															}
															if(capyHdr1 == null){
																FailData failData = new FailData();
																failData.setErrorCode("T3");
																failData.setErrorValue("Unable find the WC");
																failData.setFunctionalArea("PP");
																failData.setInterfaceID("OMPGdmStepResource");
																failData.setSourceSystem("omp");
																failData.setKey1(srcSysCd);
																failData.setKey2(mfgOrdrNum);
																failData.setKey3("");
																failData.setKey4("");
																failData.setKey5("");
																ResultObject resultObj = new ResultObject();
																resultObj.setFailData(failData);
																RawDataBuilder rawDataBuilder = new RawDataBuilder();
																rawDataBuilder.put(resultObj.getFailData().toMap());
																failMap.put("errorCode",rawDataBuilder);
																LogUtil.getCoreLog().info("Unable find the WC");
																continue;
															}
															if(capyHdr2 == null){
																FailData failData = new FailData();
																failData.setErrorCode("T7");
																failData.setErrorValue("Unable find the Secondary Resource");
																failData.setFunctionalArea("PP");
																failData.setInterfaceID("OMPGdmStepResource");
																failData.setSourceSystem("omp");
																failData.setKey1(srcSysCd);
																failData.setKey2(mfgOrdrNum);
																failData.setKey3("");
																failData.setKey4("");
																failData.setKey5("");
																ResultObject resultObject = new ResultObject();
																resultObject.setFailData(failData);
																RawDataBuilder rawDataBuilder = new RawDataBuilder();
																rawDataBuilder.put(resultObject.getFailData().toMap());
																failMap.put("errorCode",rawDataBuilder);
																LogUtil.getCoreLog().info("Unable find the Secondary Resource");
																continue;
															}else{
																String capyNm = service.checkNull4Str(capyHdr2.get("capyNm").toString());
																String plntCd4capyHdr = service.checkStrNull4Num(capyHdr2.get("plntCd").toString());
																RawDataBuilder dataRaw = new RawDataBuilder();
																String machineId = StringInner.join(srcSysCd,"_",plntCd4T3,"/",wrkCtrCd);
																String operationId = StringInner.join("PRO/",String.valueOf(Long.parseLong(mfgOrdrNum)),"/",operNum);
																String resourceId = StringInner.join(srcSysCd,"_",plntCd4capyHdr,"/",capyNm);
																String stepResourceId = StringInner.join(resourceId,"/",wrkCtrCd,"/PRO/",String.valueOf(Long.parseLong(mfgOrdrNum)),"/",operNum);

																dataRaw.put("stepResourceId",stepResourceId);
																dataRaw.put("machineId",machineId);
																dataRaw.put("minQuantity", minQuantity);
																dataRaw.put("operationId",operationId);
																dataRaw.put("resourceId",resourceId);
																dataRaw.put("active",yesDefault);
																dataRaw.put("activeOPRERP",yesDefault);
																dataRaw.put("activeSOPERP",noDefault);
																dataRaw.put("quantity",zeroDefault);
																dataRaw.put("stepResourceType",prodStr);
																rawDataBuilderList.add(dataRaw);
															}
														}
													}
												}
											}
										}
									}
								}else{
									return false;
								}
							}else{
								return false;
							}
						}else{
							return false;
						}
					}else{
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}else{
			return false;
		}

		return true;
	}


}
