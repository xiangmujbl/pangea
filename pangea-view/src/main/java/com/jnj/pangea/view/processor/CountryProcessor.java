package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.bo.CountryBo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryProcessor extends BaseProcessor implements IEventProcessor {

    private String REGION_SOURCE_SYSTEM_V1 = "/edm/source_system_v1";
    private String REGION_EMS_F_MDM_COUNTRIES = "/ems/ems_f_mdm_countries";

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.stream().forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();
            CountryBo countryBo = new CountryBo();

            // rule T1
            String zSourceSystem = getStringField(rawDataMap, "zSourceSystem");
            countryBo.setSourceSystem(getFieldWithT1(zSourceSystem));

            String localCountry = getStringField(rawDataMap, "mdmCode");
            countryBo.setLocalCountry(localCountry);

            String countryCode = getStringField(rawDataMap, "zEntCodeIso3166Alpha2");
            countryBo.setCountryCode(countryCode);

            // rule T2
            String zEntCodeIso3166Alpha2 = getStringField(rawDataMap, "zEntCodeIso3166Alpha2");
            String  mdmName = getFieldWithT2(zEntCodeIso3166Alpha2);
            countryBo.setCountryName(mdmName);

            countryBo.setConsumerPlanningRegion("");

            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(countryBo.getKey(), countryBo.toMap());
            result.add(viewRaw);
        });

        return result;
    }

    private String getFieldWithT1(String zSourceSystem) {
        if (StringUtils.isEmpty(zSourceSystem)) {
            return "";
        }
        String systemQueryString = QueryHelper.buildCriteria("localSourceSystem").is(zSourceSystem).toQueryString();
        Map.Entry<String, Map<String, Object>> systemResult = AdfViewHelper.queryForMap(REGION_SOURCE_SYSTEM_V1, systemQueryString);
        if (null != systemResult) {
            return systemResult.getValue().get("sourceSystem").toString().trim();
        }
        return "";
    }

    private String getFieldWithT2(String zEntCodeIso3166Alpha2) {

        if (null == zEntCodeIso3166Alpha2 || zEntCodeIso3166Alpha2.isEmpty()) {
            return "";
        }
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is("[EMS]").and("mdmCode").is(zEntCodeIso3166Alpha2).toQueryString();
        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(REGION_EMS_F_MDM_COUNTRIES, countryQueryString);

        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();
            return jsonObj.get("mdmName") + "";
        }
        return "";
    }
}