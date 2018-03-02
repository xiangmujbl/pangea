package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.bo.PlantBo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PantProcessor extends BaseProcessor implements IEventProcessor {

    private String REGION_SOURCE_SYSTEM_V1 = "/edm/source_system_v1";
    private String REGION_T001W = "/project_one/t001w";
    private String REGION_T001K = "/project_one/t001k";
    private String REGION_T001 = "/project_one/t001";
    private String REGION_COUNTRY_V1 = "/edm/country_v1";

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new ArrayList<>();
        events.stream().forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();
            PlantBo plantBo = new PlantBo();

            // rule T1
            String zPlantSourceSystem = getStringField(rawDataMap, "zPlantSourceSystem");
            plantBo.setSourceSystem(getFieldWithT1(zPlantSourceSystem));

            String localPlant = getStringField(rawDataMap, "zPlant");
            plantBo.setLocalPlant(localPlant);

            // rule T2
            Map<String, Object> enrich = getFieldsWithT2(localPlant);

            String name1 = "name1";
            plantBo.setLocalPlantName(getStringField(enrich, name1));

            // rule T3
            String land1 = "land1";
            String LocalCountry = getStringField(enrich, land1);
            plantBo.setLocalCountry(LocalCountry);


            String plant = getStringField(rawDataMap, "zEntPlantNumber");
            plantBo.setPlant(plant);

            plantBo.setLocalPlanningRelevant("");

            // rule T4
            plantBo.setCountry(getFieldWithT4(LocalCountry));

            String site = getStringField(rawDataMap, "zSite");
            plantBo.setSite(site);

            String nodeType = "nodetype";
            String localPlantType = getStringField(enrich, nodeType);
            plantBo.setLocalPlantType(localPlantType);

            String plantType = getStringField(rawDataMap, "zEntPlantType");
            plantBo.setPlantType(plantType);

            // rule J1
            String bwKeyDate = "bwkey";
            String bwKey = getStringField(enrich, bwKeyDate);

            plantBo.setLocalCurrency(getFieldWithJ1(bwKey));

            String region = getStringField(rawDataMap, "zRegion");
            plantBo.setRegion(region);

            LogUtil.getCoreLog().info("-----------newResultItem--------plantBo:" + plantBo.toMap());
            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(plantBo.getKey(), plantBo.toMap());
            result.add(viewRaw);
        });

        return result;
    }

    private String getFieldWithT1(String zPlantSourceSystem) {
        if (StringUtils.isEmpty(zPlantSourceSystem)) {
            return "";
        }
        String systemQueryString = QueryHelper.buildCriteria("localSourceSystem").is(zPlantSourceSystem).toQueryString();
        Map.Entry<String, Map<String, Object>> systemResult = AdfViewHelper.queryForMap(REGION_SOURCE_SYSTEM_V1, systemQueryString);
        if (null != systemResult) {
            return systemResult.getValue().get("sourceSystem") + "";
        }
        return "";
    }

    private Map<String, Object> getFieldsWithT2(String zPlant) {
        if (StringUtils.isEmpty(zPlant)) {
            return null;
        }
        String name1QueryString = QueryHelper.buildCriteria("werks").is(zPlant).toQueryString();
        Map.Entry<String, Map<String, Object>> T001wResult = AdfViewHelper.queryForMap(REGION_T001W, name1QueryString);
        if (null != T001wResult) {
            return T001wResult.getValue();
        }
        return null;
    }

    private String getFieldWithT4(String land1) {
        if (StringUtils.isEmpty(land1)) {
            return "";
        }
        String localQueryString = QueryHelper.buildCriteria("localCountry").is(land1).toQueryString();
        Map.Entry<String, Map<String, Object>> localResult = AdfViewHelper.queryForMap(REGION_COUNTRY_V1, localQueryString);
        if (null != localResult) {
            return localResult.getValue().get("countryCode") + "";
        }
        return "";
    }

    private String getFieldWithJ1(String bwkey) {
        if (StringUtils.isEmpty(bwkey)) {
            return "";
        }

        String QueryString = QueryHelper.buildCriteria("bwkey").is(bwkey).toQueryString();

        Map.Entry<String, Map<String, Object>> t001kResult = AdfViewHelper.queryForMap(REGION_T001K, QueryString);

        if (null != t001kResult && null != t001kResult.getValue()) {

            String bukrs = t001kResult.getValue().get("bukrs") + "";
            if (StringUtils.isEmpty(bukrs)) {
                return "";
            }
            QueryString = QueryHelper.buildCriteria("bukrs").is(bukrs).toQueryString();
            t001kResult = AdfViewHelper.queryForMap(REGION_T001, QueryString);

            if (null != t001kResult) {
                return t001kResult.getValue().get("waers") + "";
            }
        }
        return "";
    }
}