package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.bo.CurrencyBo;
import com.jnj.pangea.view.bo.EDMUnitofMeasureBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EDMCurrencyProcessor extends BaseProcessor implements IEventProcessor {
    private static String REGION_SOURCE_SYSTEM = "/edm/source_system_v1";
    private static String REGION_EMS_F_Z_CURRENCIES = "/ems/ems_f_z_currencies_clone";


    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();
            System.out.println(rawDataMap.toString());
            CurrencyBo currencyBo = new CurrencyBo();
            LogUtil.getCoreLog().info("rawDataMap:"+rawDataMap);
            //rule T1
            String zSourceSystem = getStringField(rawDataMap, "zSourceSystem");
            if("[EMS]".equals(zSourceSystem)){
                return;
            }
            String sourceSystem = getFieldWithT1(zSourceSystem);
            currencyBo.setSourceSystem(sourceSystem);
            //rule T2
            String zEntCodeIso4217Alpha = getStringField(rawDataMap, "zEntCodeIso4217Alpha");
            String zName = getFieldWithT2(zEntCodeIso4217Alpha);
            currencyBo.setCurrencyName(zName);
            currencyBo.setLocalCurrency(getStringField(rawDataMap, "zCode"));
            currencyBo.setCurrencyCode(getStringField(rawDataMap, "zEntCodeIso4217Alpha"));
            currencyBo.setIsoNumeric(getStringField(rawDataMap, "zIso4217Numeric"));
            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(currencyBo.getKey(), currencyBo.toMap());
            LogUtil.getCoreLog().info("result:"+currencyBo.toString());
            result.add(viewRaw);
        });
        return result;
    }

    private String getFieldWithT1(String zSourceSystem) {
        //filter
        if(null==zSourceSystem||zSourceSystem.isEmpty()){
            return "";
        }
        String queryEnString = QueryHelper.buildCriteria("localSourceSystem").is(zSourceSystem).toQueryString();
        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(REGION_SOURCE_SYSTEM, queryEnString);

        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();
            String sourceSystem = jsonObj.get("sourceSystem") + "";
           return sourceSystem;
        }
        return "";
    }

    private String getFieldWithT2(String zEntCodeIso4217Alpha) {
        if(null==zEntCodeIso4217Alpha||zEntCodeIso4217Alpha.isEmpty()){
            return "";
        }
        String queryEnString = QueryHelper.buildCriteria("zSourceSystem").is("[EMS]").and("zCode").is(zEntCodeIso4217Alpha).toQueryString();
        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(REGION_EMS_F_Z_CURRENCIES, queryEnString);
        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();
            String zName = jsonObj.get("zName") + "";
            return zName;
        }
        return "";
    }




}