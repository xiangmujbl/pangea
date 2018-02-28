package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.actors.remote.CurationRawDataHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.data.raw.RawDataBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class country implements IEventProcessor {

    private String REGION_EMS_F_MDM_COUNTRIES = "/ems/ems_f_mdm_countries";
    private String REGION_SOURCE_SYSTEM_V1 = "/edm/source_system_v1";

    private List<ViewResultItem> result = new ArrayList<>();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        events.stream().forEach(raw -> {
            RawDataBuilder builder = new RawDataBuilder();
            RawDataValue rawValue = raw.getValue();
            String buildFlag = buildView(rawValue,builder);
            Map<String, Object> rawDataMap= convertMap(builder);
            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(buildFlag, rawDataMap);
            result.add(viewRaw);
        });
        return result;
    }

    private Map<String,Object> convertMap(RawDataBuilder builder)
    {
        return (Map<String, Object>)builder.toRawData();
    }


    private String buildView(RawDataValue inventory,RawDataBuilder productBuilder){

        // rule T1
        String sourceSystem = "";
        String zSourceSystem = inventory.getField("zSourceSystem").toString().trim();
        String systemQueryString = QueryHelper.buildCriteria("localSourceSystem")
                .is(zSourceSystem).toQueryString();
        RawDataValue systemResult = CurationRawDataHelper.getInstance().fetchByKey(REGION_SOURCE_SYSTEM_V1,systemQueryString);

        if(systemResult == null ){

            productBuilder.put("sourceSystem",sourceSystem);

        }else{

            sourceSystem = systemResult.getField("sourceSystem").toString().trim();

            productBuilder.put("sourceSystem",sourceSystem);

        }

        String localCountry=inventory.getField("mdmCode").toString().trim();
        String countryCode=inventory.getField("zEntCodeIso3166Alpha2").toString().trim();

        productBuilder.put("localCountry",localCountry);
        productBuilder.put("countryCode",countryCode);

        // rule T2
        String countryName = "";
        String zEntCodeIso3166Alpha2 = inventory.getField("zEntCodeIso3166Alpha2").toString().trim();
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is("[EMS]").and("mdmCode").is(zEntCodeIso3166Alpha2).toQueryString();
        RawDataValue countryResult = CurationRawDataHelper.getInstance().fetchByKey(REGION_EMS_F_MDM_COUNTRIES,countryQueryString);
       // if initial, leave blank
       if(countryResult == null ){

          productBuilder.put("countryName",countryName);

        }else{

        countryName = countryResult.getField("mdmName").toString().trim();

        productBuilder.put("countryName",countryName);

        }

        productBuilder.put("consumerPlanningRegion","");

        JsonObject productKey = JsonObject.create();
        productKey.append("sourceSystem", sourceSystem).append("localCountry",localCountry);
        return productKey.toJson();
    }

}