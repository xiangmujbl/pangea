package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.bo.CountryBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryProcessor extends BaseProcessor implements IEventProcessor {

    private String REGION_SOURCE_SYSTEM_V1 = "/edm/source_system_v1";
    private static String SOURCESYSTEM="[EMS]";

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

            String localCountry = getStringField(rawDataMap, "localCountry");
            countryBo.setLocalCountry(localCountry);

            String countryCode = getStringField(rawDataMap, "countryCode");
            countryBo.setCountryCode(countryCode);

            // rule T2
            String mdmName = "";
            String zEntCodeIso3166Alpha2 = getStringField(rawDataMap, "zEntCodeIso3166Alpha2");
            String mdmCode = getStringField(rawDataMap, "mdmCode");

            if (SOURCESYSTEM.equals(zSourceSystem) && mdmCode.equals(zEntCodeIso3166Alpha2)){

                mdmName = getStringField(rawDataMap, "mdmName");
                countryBo.setCountryName(mdmName);

            }else{

                countryBo.setCountryName(mdmName);
            }

            countryBo.setConsumerPlanningRegion("");

            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(countryBo.getKey(), countryBo.toMap());
            result.add(viewRaw);
        });

        return result;
    }

    private String getFieldWithT1(String zSourceSystem) {
        String systemQueryString = QueryHelper.buildCriteria("localSourceSystem").is(zSourceSystem).toQueryString();
        Map.Entry<String, Map<String, Object>> systemResult = AdfViewHelper.queryForMap(REGION_SOURCE_SYSTEM_V1, systemQueryString);
        if (null != systemResult) {
            return systemResult.getValue().get("sourceSystem").toString().trim();
        }
        return "";
    }

}