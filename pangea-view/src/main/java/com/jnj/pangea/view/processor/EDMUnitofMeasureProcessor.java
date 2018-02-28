package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.actors.remote.CurationRawDataHelper;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.bo.EDMUnitofMeasureBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EDMUnitofMeasureProcessor extends BaseProcessor implements IEventProcessor {
    private static String REGION_SOURCE_SYSTEM = "/edm/source_system_v1";
    private static String REGION_EMS_F_MDM_UNITS = "/ems/ems_f_mdm_units_clone";


    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();
            System.out.println(rawDataMap.toString());
            EDMUnitofMeasureBo edmUnitofMeasureBo = new EDMUnitofMeasureBo();

            //rule T1
            String zSourceSystem = getStringField(rawDataMap, "zSourceSystem");
            if("[EMS]".equals(zSourceSystem)){
                return;
            }
            String sourceSystem = getFieldWithT1(zSourceSystem);
            edmUnitofMeasureBo.setSourceSystem(sourceSystem);
            //rule T2
            String zEnterPriseCode = getStringField(rawDataMap, "zEnterpriseCode");
            String uomName = getFieldWithT2(zEnterPriseCode);
            edmUnitofMeasureBo.setUomName(uomName);

            edmUnitofMeasureBo.setLocalUom(getStringField(rawDataMap, "mdmSapCode"));
            edmUnitofMeasureBo.setLocalUomName(getStringField(rawDataMap, "mdmName"));
            edmUnitofMeasureBo.setUom(zEnterPriseCode);
            edmUnitofMeasureBo.setIsocode(getStringField(rawDataMap, "mdmIsoCode"));
            edmUnitofMeasureBo.setMeasure(getStringField(rawDataMap, "zUnitsDimension"));
            edmUnitofMeasureBo.setFactor("");
            edmUnitofMeasureBo.setRoundingDecimal("");
            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(edmUnitofMeasureBo.getKey(), edmUnitofMeasureBo.toMap());
            result.add(viewRaw);
        });
        return result;
    }

    private String getFieldWithT1(String zSourceSystem) {
        //filter

        String queryEnString = QueryHelper.buildCriteria("localSourceSystem").is(zSourceSystem).toQueryString();
        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(REGION_SOURCE_SYSTEM, queryEnString);

        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();
            String sourceSystem = jsonObj.get("sourceSystem") + "";
           return sourceSystem;
        }
        return "";
    }

    private String getFieldWithT2(String zEnterpriseCode) {

        String queryEnString = QueryHelper.buildCriteria("zSourceSystem").is("[EMS]").and("mdmSapCode").is(zEnterpriseCode).toQueryString();
        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(REGION_EMS_F_MDM_UNITS, queryEnString);
        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();
            String mdm_name = jsonObj.get("mdmName") + "";
            return mdm_name;
        }
        return "";
    }




}